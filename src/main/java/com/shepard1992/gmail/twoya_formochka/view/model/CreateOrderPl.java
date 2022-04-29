package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateOrderPl {

    private Long idClient;

    private List<ItemsOrderPl> items;

    private Long idDiscount;

    private Double price;

}
