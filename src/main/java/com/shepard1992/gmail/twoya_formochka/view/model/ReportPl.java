package com.shepard1992.gmail.twoya_formochka.view.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportPl {

    private Integer year;

    private Integer quarter;

    private Double income;

    private Integer tax;

    private Double sumForTax;

    private Double clearIncome;

}
