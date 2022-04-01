package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ClientPl {

    private Long id;

    private String firstName;

    private String lastName;

    private String secondName;

    private Date birthday;

    private AddressPl address;

    private String email;

    private String telephone;

    private Set<DiscountPl> discounts;

    private List<OrderPl> orders;

}
