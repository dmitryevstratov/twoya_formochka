package view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.OrderController;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderToUpdatePl;
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
import static org.mockito.Mockito.when;

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

}
