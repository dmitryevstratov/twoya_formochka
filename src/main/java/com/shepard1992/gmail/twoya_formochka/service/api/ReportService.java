package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.view.model.CreateReportPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ReportPl;

import java.util.List;

public interface ReportService {

    ReportPl addReport(CreateReportPl createReportPl);

    List<ReportPl> searchByParams(Integer year, Integer quarter);

}
