package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.CreateReportPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ReportPl;

import java.util.List;

public interface ReportController {

    ReportPl addReport(CreateReportPl createReportPl);

    List<ReportPl> searchByParams(Integer year, Integer quarter);

}
