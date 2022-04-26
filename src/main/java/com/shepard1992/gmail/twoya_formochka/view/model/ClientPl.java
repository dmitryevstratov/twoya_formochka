package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientPl {

    private Long id;

    private String firstName;

    private String lastName;

    private String secondName;

    private String birthday;

    private AddressPl address;

    private String email;

    private String telephone;

    private List<DiscountPl> discounts;

    private List<OrderPl> orders;

}
