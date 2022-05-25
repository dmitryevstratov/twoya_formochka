package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.api.Report;
import com.shepard1992.gmail.twoya_formochka.view.model.ReportPl;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportPl mapperToReportPl(Report report) {
        return ReportPl.builder()
                .year(report.getYear())
                .quarter(report.getQuarter())
                .tax(report.getTax())
                .income(report.getIncome())
                .sumForTax(report.getSumForTax())
                .clearIncome(report.getClearIncome())
                .build();
    }

}
