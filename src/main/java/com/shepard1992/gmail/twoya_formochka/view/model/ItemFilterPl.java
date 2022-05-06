package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemFilterPl {

    private Integer id;

    private String name;

    private String type;

    private String category;

    private Double size;

}
