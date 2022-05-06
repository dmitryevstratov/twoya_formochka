package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DiscountPl {

    private Integer id;

    private DiscountTypePl type;

    private Integer value;

    private List<ClientPl> clients;
}
