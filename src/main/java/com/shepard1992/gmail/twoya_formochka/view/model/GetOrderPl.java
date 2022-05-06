package com.shepard1992.gmail.twoya_formochka.view.model;

import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderPl {

    private Integer id;

    private ClientPl client;

    private String dateCreate;

    private String dateClosed;

    private DiscountPl discount;

    private Double totalPrice;

    private Integer countItems;

    private StatusOrder status;

}
