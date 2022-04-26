package com.shepard1992.gmail.twoya_formochka.view.model;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ItemPl {

    private Long id;

    private String name;

    private ItemTypePl type;

    private ItemCategoryPl category;

    private Double size;

    private Double price;

    private List<Order> orders;

}
