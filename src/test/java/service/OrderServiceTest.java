package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.specification.OrderSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderToUpdatePl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemsOrderPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import service.config.OrderServiceTestConfig;
import view.stubs.FilterOrderPlStub;
import view.stubs.OrderStub;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@Import({
        MapperTestConfig.class,
        OrderServiceTestConfig.class
})
public class OrderServiceTest {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderRepository repository;

    @Test
    public void when_call_addOrder_then_return_result() {
        when(repository.save(any())).thenReturn(Order.builder()
                .client(Client.builder()
                        .id(1)
                        .build())
                .items(List.of(Item.builder().build()))
                .build());

        CreateOrderPl createOrderPl = service.addOrder(CreateOrderPl.builder()
                .idClient(1)
                .items(List.of(ItemsOrderPl.builder()
                        .count(1)
                        .id(1)
                        .build()))
                .price(100.0)
                .build());

        assertEquals(1L, createOrderPl.getIdClient().longValue());
    }

    @Test
    public void test_when_call_getOrders_then_return_result() {
        when(repository.findAll()).thenReturn(List.of(OrderStub.getStub()));

        List<GetOrderPl> orders = service.getOrders();

        assertEquals(1, orders.size());
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(repository.findAll(any(OrderSpecification.class))).thenReturn(Collections.singletonList(OrderStub.getStub()));

        List<GetOrderPl> orderPlList = service.searchByParams(FilterOrderPlStub.getStub());

        assertNotNull(orderPlList);
        assertEquals(1, orderPlList.size());
    }

    @Test
    public void test_when_call_getOrderById_then_return_result(){
        when(repository.findById(any())).thenReturn(Optional.of(OrderStub.getStub()));

        GetOrderToUpdatePl order = service.getOrderById(1);

        assertNotNull(order);
    }

    @Test
    public void when_call_editOrder_then_return_result() {
        when(repository.save(any())).thenReturn(Order.builder()
                .client(Client.builder()
                        .id(1)
                        .build())
                .items(List.of(Item.builder().build()))
                .build());

        CreateOrderPl createOrderPl = service.editOrders(CreateOrderPl.builder()
                .idClient(1)
                .items(List.of(ItemsOrderPl.builder()
                        .count(1)
                        .id(1)
                        .build()))
                .price(100.0)
                .build());

        assertEquals(1L, createOrderPl.getIdClient().longValue());
    }

    @Test
    public void test_when_call_deleteOrderById_then_return_success() {
        doNothing().when(repository).deleteById(any());

        service.deleteOrderById(1);

        verify(repository, times(1)).deleteById(any());
    }

}
