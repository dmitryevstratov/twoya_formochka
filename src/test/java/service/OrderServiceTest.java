package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.*;
import com.shepard1992.gmail.twoya_formochka.repository.specification.OrderSpecification;
import com.shepard1992.gmail.twoya_formochka.repository.specification.OrderStatisticSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import service.config.OrderServiceTestConfig;
import stubs.FilterOrderPlStub;
import stubs.OrderStub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Autowired
    private ClientRepository clientRepository;

    @Before
    public void clear() {
        reset(repository);
    }

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
    public void test_when_call_getOrderById_then_return_result() {
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

    @Test
    public void test_when_call_getOrdersStatus_then_return_success() {
        when(repository.findAll()).thenReturn(List.of(OrderStub.getStub()));

        List<GetOrderPl> ordersStatus = service.getOrdersStatus();

        assertEquals(OrderStub.getStub().getId(), ordersStatus.get(0).getId());
    }

    @Test
    public void test_when_call_editOrderStatus_then_return_success() {
        when(repository.save(any())).thenReturn(OrderStub.getStub());
        when(repository.findById(any())).thenReturn(Optional.of(OrderStub.getStub()));

        service.editOrderStatus(1, "SENT");

        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void test_when_call_editOrderStatus_with_status_paid_then_return_success() {
        when(repository.findById(any())).thenReturn(Optional.of(OrderStub.getStub()));
        when(clientRepository.findById(any())).thenReturn(Optional.of(Client.builder().id(100)
                .discounts(List.of(Discount.builder()
                                .id(3)
                                .value(200)
                                .type(DiscountType.builder()
                                        .id(1)
                                        .name("ДР")
                                        .build())
                                .build(),
                        Discount.builder()
                                .id(4)
                                .value(100)
                                .type(DiscountType.builder()
                                        .id(1)
                                        .name("НГ")
                                        .build())
                                .build()))
                .build()));

        service.editOrderStatus(1, "PAID");

        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(any());
        verify(clientRepository, times(1)).findById(any());
        verify(clientRepository, times(1)).save(any());
    }

    @Test
    public void test_when_call_searchByParams_with_status_paid_then_return_success() {
        List<Pair<String, String>> pairs = new ArrayList<>();
        pairs.add(Pair.of("01-01-2020", "01-01-2022"));
        pairs.add(Pair.of("01-01-2020", ""));
        pairs.add(Pair.of("", ""));

        for (Pair<String, String> pair : pairs) {
            if (pair.getFirst().equals("")) {
                when(repository.findAll()).thenReturn(List.of(OrderStub.getStub()));
            } else {
                when(repository.findAll(any(OrderStatisticSpecification.class))).thenReturn(List.of(OrderStub.getStub()));
            }

            List<GetMonthStatisticPl> statisticPls = service.searchByParams(pair.getFirst(), pair.getSecond());

            assertNotNull(statisticPls);
        }

    }

}
