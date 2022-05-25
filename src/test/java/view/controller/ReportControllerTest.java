package view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ReportService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ReportController;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateReportPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ReportPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import view.config.ViewTestConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(ViewTestConfig.class)
public class ReportControllerTest {

    @Autowired
    private ReportService service;

    @Autowired
    private ReportController controller;

    @Test
    public void when_call_addReport_then_return_result() {
        when(service.addReport(any())).thenReturn(ReportPl.builder()
                .year(1900)
                .quarter(2)
                .tax(5)
                .build());

        ReportPl reportPl = controller.addReport(CreateReportPl.builder()
                .year(1900)
                .quarter(2)
                .tax(5)
                .build());

        assertEquals(1900, reportPl.getYear());
        assertEquals(2, reportPl.getQuarter());
        assertEquals(5, reportPl.getTax());
    }

    @Test
    public void when_call_searchByParams_return_result() {
        when(service.searchByParams(anyInt(), anyInt())).thenReturn(List.of(
                ReportPl.builder()
                        .year(1990)
                        .quarter(3)
                        .tax(1)
                        .build()
        ));

        ReportPl reportPl = controller.searchByParams(1900, 3).get(0);

        assertEquals(1990, reportPl.getYear());
        assertEquals(3, reportPl.getQuarter());
        assertEquals(1, reportPl.getTax());
    }

}
