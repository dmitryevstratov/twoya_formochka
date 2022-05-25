package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateReportPl {

    private Integer year;

    private Integer quarter;

    private Integer tax;

}
