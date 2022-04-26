package com.shepard1992.gmail.twoya_formochka.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filter {

    private Long id;

    private String firstName;

    private String lastName;

    private String secondName;

    private String birthday;

    private String email;

    private String telephone;

}
