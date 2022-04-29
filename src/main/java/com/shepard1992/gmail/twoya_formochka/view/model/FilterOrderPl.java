package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class FilterOrderPl {

    private Long id;

    private String firstName;

    private String lastName;

    private String dateCreate;

    private String dateClosed;

    private String selectedStatus;

    private String priceMin;

    private String priceMax;

    private String count;

}
