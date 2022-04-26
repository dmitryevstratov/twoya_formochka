package com.shepard1992.gmail.twoya_formochka.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemFilter {

    private Long id;

    private String name;

    private String type;

    private String category;

    private Double size;

}
