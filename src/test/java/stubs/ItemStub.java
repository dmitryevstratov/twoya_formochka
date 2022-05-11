package stubs;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemCategory;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemType;

public class ItemStub {

    private ItemStub() {
    }

    public static Item getStub() {
        return Item.builder()
                .id(1)
                .name("Машинка")
                .price(10.0)
                .size(9.0)
                .category(ItemCategory.builder()
                        .id(1)
                        .name("Автомобили")
                        .build())
                .type(ItemType.builder()
                        .id(1)
                        .name("Оттиск")
                        .build())
                .build();
    }
}
