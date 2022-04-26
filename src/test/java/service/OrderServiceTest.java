package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemsOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import service.config.OrderServiceTestConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
                        .id(1L)
                        .build())
                .items(List.of(Item.builder().build()))
                .build());

        OrderPl orderPl = service.addOrder(OrderPl.builder()
                .idClient(1L)
                .items(List.of(ItemsOrderPl.builder()
                        .count(1)
                        .id(1L)
                        .build()))
                .price(100.0)
                .build());

        assertEquals(1L, orderPl.getIdClient().longValue());
    }

}
