package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateOrderPl {

    private Integer idOrder;

    private Integer idClient;

    private List<ItemsOrderPl> items;

    private Integer idDiscount;

    private Double price;

}
