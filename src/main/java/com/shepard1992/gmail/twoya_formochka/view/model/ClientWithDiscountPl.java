package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientWithDiscountPl {

    private Integer id;

    private List<DiscountPl> discounts;

}
