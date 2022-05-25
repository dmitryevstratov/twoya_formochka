package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterClientPl {

    private Integer id;

    private String firstName;

    private String lastName;

    private String secondName;

    private String birthday;

    private String email;

    private String telephone;

    private String discountName;

}
