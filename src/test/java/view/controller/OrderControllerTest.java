package view.controller;

import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.OrderController;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetMonthStatisticPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderToUpdatePl;
import com.shepard1992.gmail.twoya_formochka.view.model.enums.Month;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import view.config.ViewTestConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import(ViewTestConfig.class)
public class OrderControllerTest {

    @Autowired
    public OrderService service;

    @Autowired
    public OrderController controller;

    @Test
    public void test_when_call_addOrder_then_return_result() {
        when(service.addOrder(any())).thenReturn(CreateOrderPl.builder()
                .idClient(1)
                .build());

        CreateOrderPl createOrderPl = controller.addOrder(CreateOrderPl.builder()
                .idClient(1)
                .build());

        assertEquals(1L, createOrderPl.getIdClient().longValue());
    }

    @Test
    public void test_when_call_getOrders_then_return_result() {
        when(service.getOrders()).thenReturn(List.of(GetOrderPl.builder().build()));

        List<GetOrderPl> orders = controller.getOrders();

        assertEquals(1, orders.size());
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(service.searchByParams(any())).thenReturn(List.of(GetOrderPl.builder().build()));

        List<GetOrderPl> orders = controller.searchByParams(1, "", "", "", "", "", "", "", "");

        assertEquals(1, orders.size());
    }

    @Test
    public void test_when_call_getOrderById_then_return_result() {
        when(service.getOrderById(any())).thenReturn(GetOrderToUpdatePl.builder().build());

        GetOrderToUpdatePl order = controller.getOrderById(1);

        assertNotNull(order);
    }

    @Test
    public void test_when_call_editOrder_then_return_result() {
        when(service.editOrders(any())).thenReturn(CreateOrderPl.builder()
                .idClient(1)
                .build());

        CreateOrderPl createOrderPl = controller.editOrders(CreateOrderPl.builder()
                .idClient(1)
                .build());

        assertEquals(1L, createOrderPl.getIdClient().longValue());
    }

    @Test
    public void test_when_call_deleteOrderById_then_return_result() {

        doNothing().when(service).deleteOrderById(any());

        controller.deleteOrderById(1);

        verify(service, times(1)).deleteOrderById(any());

    }

    @Test
    public void test_when_call_editOrderStatus_then_return_success() {
        when(service.editOrderStatus(anyInt(), anyString())).thenReturn(CreateOrderPl.builder()
                .idOrder(4)
                .build());

        CreateOrderPl orderPl = controller.editOrderStatus(1, "SENT");

        assertEquals(Integer.valueOf(4), orderPl.getIdOrder());
    }

    @Test
    public void test_when_call_getOrdersStatus_then_return_success() {
        when(service.getOrdersStatus()).thenReturn(List.of(GetOrderPl.builder()
                .id(1)
                .status(StatusOrder.CREATED)
                .build()));

        GetOrderPl orderPl = controller.getOrdersStatus().get(0);

        assertEquals(Integer.valueOf(1), orderPl.getId());
        assertEquals(StatusOrder.CREATED, orderPl.getStatus());
    }

    @Test
    public void test_when_call_searchByParams_then_return_success() {
        when(service.searchByParams(anyString(), anyString())).thenReturn(List.of(GetMonthStatisticPl.builder()
                .year(1990)
                .month(Month.AUG)
                .build()));

        List<GetMonthStatisticPl> statisticPls = controller.searchByParams("01-01-2020", "05-05-2022");

        assertEquals(Integer.valueOf(1990), statisticPls.get(0).getYear());
        assertEquals(Month.AUG, statisticPls.get(0).getMonth());
    }

}
