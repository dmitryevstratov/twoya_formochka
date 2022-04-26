package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemCategory;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemType;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemCategoryPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemTypePl;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    /*public Item mapperToItem(ItemPl itemPl) {
        return Item.builder()
                .id(itemPl.getId())
                .name(itemPl.getName())
                .type(ItemType.builder()
                        .id(itemPl.getType().getId())
                        .name(itemPl.getType().getName())
                        .build())
                .category(ItemCategory.builder()
                        .id(itemPl.getCategory().getId())
                        .name(itemPl.getCategory().getName())
                        .build())
                .size(itemPl.getSize())
                .price(itemPl.getPrice())
                .build();
    }*/

    public ItemPl mapperToItemPl(Item item) {
        return ItemPl.builder()
                .id(item.getId())
                .name(item.getName())
                .type(ItemTypePl.builder()
                        .id(item.getType().getId())
                        .name(item.getType().getName())
                        .build())
                .category(ItemCategoryPl.builder()
                        .id(item.getCategory().getId())
                        .name(item.getCategory().getName())
                        .build())
                .size(item.getSize())
                .price(item.getPrice())
                .build();
    }

}
