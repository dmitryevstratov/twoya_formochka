package com.shepard1992.gmail.twoya_formochka.repository.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class FilterOrder {

    private Integer id;

    private String firstName;

    private String lastName;

    private ZonedDateTime dateCreate;

    private ZonedDateTime dateClosed;

    private String selectedStatus;

    private String priceMin;

    private String priceMax;

    private String count;

}
