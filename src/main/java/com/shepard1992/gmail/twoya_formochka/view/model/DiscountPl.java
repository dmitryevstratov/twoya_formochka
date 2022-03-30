package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiscountPl {

    private Long id;

    private DiscountTypePl type;

    private Integer value;
}
