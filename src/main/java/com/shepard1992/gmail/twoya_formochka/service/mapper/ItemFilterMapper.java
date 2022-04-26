package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.dto.ItemFilter;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import org.springframework.stereotype.Component;

@Component
public class ItemFilterMapper {

    public ItemFilter mapperToFilter(ItemFilterPl filterPl) {
        return ItemFilter.builder()
                .id(filterPl.getId())
                .name(filterPl.getName())
                .category(filterPl.getCategory())
                .type(filterPl.getType())
                .size(filterPl.getSize())
                .build();
    }

}
