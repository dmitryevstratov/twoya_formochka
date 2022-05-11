package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.service.mapper.OrderMapper;
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
import stubs.OrderStub;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test_mapperToOrder() {
        CreateOrderPl createOrderPl = CreateOrderPl.builder()
                .idClient(1)
                .idDiscount(1)
                .price(200.1)
                .items(List.of(ItemsOrderPl.builder()
                                .id(5)
                                .count(2)
                                .build(),
                        ItemsOrderPl.builder()
                                .id(5)
                                .count(1)
                                .build(),
                        ItemsOrderPl.builder()
                                .id(1)
                                .count(1)
                                .build()))
                .build();

        Order order = orderMapper.mapperToOrder(createOrderPl);

        assertEquals(createOrderPl.getIdClient(), order.getClient().getId());
        assertEquals(createOrderPl.getIdDiscount(), order.getDiscount().getId());
        assertEquals(createOrderPl.getPrice(), order.getTotalPrice());
        assertEquals(createOrderPl.getItems().get(0).getId(), order.getItems().get(0).getId());
    }

    @Test
    public void test_mapperToOrderPl() {
        CreateOrderPl createOrderPl = orderMapper.mapperToOrderPl(OrderStub.getStub());

        assertEquals(createOrderPl.getIdClient(), OrderStub.getStub().getClient().getId());
        assertEquals(createOrderPl.getIdDiscount(), OrderStub.getStub().getDiscount().getId());
        assertEquals(createOrderPl.getPrice(), OrderStub.getStub().getTotalPrice());
        assertEquals(createOrderPl.getItems().get(0).getId(), OrderStub.getStub().getItems().get(0).getId());
    }

    @Test
    public void test_mapperToGetOrderPl() {
        GetOrderPl orderPl = orderMapper.mapperToGetOrderPl(OrderStub.getStub());

        assertEquals(OrderStub.getStub().getId(), orderPl.getId());
        assertEquals(OrderStub.getStub().getClient().getId(), orderPl.getClient().getId());
        assertEquals(OrderStub.getStub().getDateCreate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), orderPl.getDateCreate());
        assertEquals(OrderStub.getStub().getDateClosed().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), orderPl.getDateClosed());
        assertEquals(OrderStub.getStub().getStatus(), orderPl.getStatus());
        assertEquals(OrderStub.getStub().getTotalPrice(), orderPl.getTotalPrice());
    }

    @Test
    public void test_mapperToUpdateOrderPl(){
        GetOrderToUpdatePl updatePl = orderMapper.mapperToUpdateOrderPl(OrderStub.getStub());

        assertEquals(OrderStub.getStub().getClient().getId(), updatePl.getClientPl().getId());
        assertEquals(OrderStub.getStub().getDiscount().getId(), updatePl.getDiscountPl().getId());
        assertEquals(OrderStub.getStub().getItems().get(0).getId(), updatePl.getItemPlList().get(0).getId());
    }
}
