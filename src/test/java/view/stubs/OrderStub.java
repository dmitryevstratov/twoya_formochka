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
                .id(1)
                .discount(Discount.builder()
                        .id(3)
                        .value(200)
                        .type(DiscountType.builder()
                                .id(1)
                                .name("Оттиск")
                                .build())
                        .build())
                .totalPrice(200.0)
                .items(List.of(Item.builder()
                        .type(ItemType.builder()
                                .id(1)
                                .build())
                        .category(ItemCategory.builder()
                                .id(1)
                                .build())
                        .id(12)
                        .build()))
                .dateCreate(ZonedDateTime.now())
                .dateClosed(ZonedDateTime.now())
                .client(Client.builder().id(100).build())
                .status(StatusOrder.CREATED)
                .build();
    }
}
