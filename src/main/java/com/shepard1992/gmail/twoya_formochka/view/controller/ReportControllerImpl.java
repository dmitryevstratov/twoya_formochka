package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ReportService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ReportController;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateReportPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ReportPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ReportControllerImpl implements ModelAndViewController, ReportController {

    private final ReportService service;

    @Autowired
    public ReportControllerImpl(ReportService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/tax.html")
    public ModelAndView getView(Model model) {
        return new ModelAndView("tax");
    }

    @Override
    @PostMapping("/tax/create")
    public ReportPl addReport(@RequestBody CreateReportPl createReportPl) {
        return service.addReport(createReportPl);
    }

    @Override
    @GetMapping("/tax/search")
    public List<ReportPl> searchByParams(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "quarter", required = false) Integer quarter
    ) {
        return service.searchByParams(year, quarter);
    }

}
