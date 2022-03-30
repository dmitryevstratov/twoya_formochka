package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ClientPl {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String secondName;

    private final Date birthday;

    private final AddressPl address;

    private final String email;

    private final String telephone;

    private final Set<DiscountPl> discounts;

    private final List<OrderPl> orders;

}
