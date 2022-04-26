package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.*;
import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import com.shepard1992.gmail.twoya_formochka.service.mapper.OrderMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemsOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test_mapperToOrder() {
        OrderPl orderPl = OrderPl.builder()
                .idClient(1L)
                .idDiscount(1L)
                .price(200.1)
                .items(List.of(ItemsOrderPl.builder()
                                .id(5L)
                                .count(2)
                                .build(),
                        ItemsOrderPl.builder()
                                .id(5L)
                                .count(1)
                                .build(),
                        ItemsOrderPl.builder()
                                .id(1L)
                                .count(1)
                                .build()))
                .build();

        Order order = orderMapper.mapperToOrder(orderPl);

        assertEquals(orderPl.getIdClient(), order.getClient().getId());
        assertEquals(orderPl.getIdDiscount(), order.getDiscount().getId());
        assertEquals(orderPl.getPrice(), order.getTotalPrice());
        assertEquals(orderPl.getItems().get(0).getId(), order.getItems().get(0).getId());
    }

    @Test
    public void test_mapperToOrderPl() {
        Order order = Order.builder()
                .id(1L)
                .discount(Discount.builder()
                        .id(3L)
                        .value(200)
                        .type(DiscountType.builder()
                                .id(1L)
                                .name("Оттиск")
                                .build())
                        .build())
                .totalPrice(200.0)
                .items(List.of(Item.builder()
                        .id(12L)
                        .build()))
                .dateCreate(ZonedDateTime.now())
                .client(Client.builder().id(100L).build())
                .status(StatusOrder.CREATED)
                .build();

        OrderPl orderPl = orderMapper.mapperToOrderPl(order);

        assertEquals(orderPl.getIdClient(), order.getClient().getId());
        assertEquals(orderPl.getIdDiscount(), order.getDiscount().getId());
        assertEquals(orderPl.getPrice(), order.getTotalPrice());
        assertEquals(orderPl.getItems().get(0).getId(), order.getItems().get(0).getId());
    }

}
