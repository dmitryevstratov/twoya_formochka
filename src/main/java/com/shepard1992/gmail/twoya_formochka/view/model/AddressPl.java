package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressPl {

    private Integer id;

    private String country;

    private String region;

    private String locality;

    private String street;

    private Integer room;

    private Integer index;

    private String additionalInfo;

}
