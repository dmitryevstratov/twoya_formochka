package view.stubs;

import com.shepard1992.gmail.twoya_formochka.repository.entity.*;
import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderStub {

    private OrderStub() {
    }

    public static Order getStub() {
        return Order.builder()
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
                .dateClosed(ZonedDateTime.now())
                .client(Client.builder().id(100L).build())
                .status(StatusOrder.CREATED)
                .build();
    }
}
