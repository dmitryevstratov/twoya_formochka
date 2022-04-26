package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DiscountTypePl {

    private Long id;

    private String name;

    private List<DiscountPl> discounts;

}
