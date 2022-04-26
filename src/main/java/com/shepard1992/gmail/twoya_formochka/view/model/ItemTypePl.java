package com.shepard1992.gmail.twoya_formochka.view.model;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ItemTypePl {

    private Long id;

    private String name;

    private List<Item> items;

}
