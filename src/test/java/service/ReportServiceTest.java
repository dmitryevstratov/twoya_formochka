package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Report;
import com.shepard1992.gmail.twoya_formochka.repository.api.ReportRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.service.api.ReportService;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateReportPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ReportPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import service.config.ReportServiceTestConfig;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import({
        MapperTestConfig.class,
        ReportServiceTestConfig.class
})
public class ReportServiceTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReportService service;

    @Test
    public void when_call_addReport_then_return_result() {
        when(reportRepository.findByYearAndQuarter(anyInt(), anyInt())).thenReturn(Report.builder()
                .year(1990)
                .quarter(2)
                .id(1)
                .build());

        when(orderRepository.getOrdersByYearAndMonth(anyInt(), anyString())).thenReturn(List.of(
                Order.builder()
                        .dateCreate(ZonedDateTime.now())
                        .totalPrice(100.0)
                        .build()
        ));
        when(reportRepository.save(any())).thenReturn(Report.builder()
                .id(2)
                .quarter(2)
                .year(1900)
                .income(100.0)
                .tax(5)
                .sumForTax(5.0)
                .clearIncome(95.0)
                .build());

        ReportPl reportPl = service.addReport(CreateReportPl.builder()
                .year(1900)
                .tax(3)
                .quarter(4)
                .build());

        assertEquals(1900, reportPl.getYear());
        assertEquals(2, reportPl.getQuarter());
        assertEquals(5, reportPl.getTax());
    }

    @Test
    public void when_call_addReport_with_empty_report_then_return_result() {
        when(reportRepository.findByYearAndQuarter(anyInt(), anyInt())).thenReturn(null);

        when(orderRepository.getOrdersByYearAndMonth(anyInt(), anyString())).thenReturn(List.of(
                Order.builder()
                        .dateCreate(ZonedDateTime.now())
                        .totalPrice(100.0)
                        .build()
        ));
        when(reportRepository.save(any())).thenReturn(Report.builder()
                .id(2)
                .quarter(2)
                .year(1900)
                .income(100.0)
                .tax(5)
                .sumForTax(5.0)
                .clearIncome(95.0)
                .build());

        ReportPl reportPl = service.addReport(CreateReportPl.builder()
                .year(1900)
                .tax(3)
                .quarter(4)
                .build());

        assertEquals(1900, reportPl.getYear());
        assertEquals(2, reportPl.getQuarter());
        assertEquals(5, reportPl.getTax());
    }

    @Test
    public void when_call_searchByParams_with_year_and_quarter_then_return_result() {
        when(reportRepository.findByYearAndQuarter(anyInt(), anyInt())).thenReturn(Report.builder()
                .id(1)
                .year(2000)
                .quarter(1)
                .tax(10)
                .build());

        ReportPl reportPl = service.searchByParams(2000, 1).get(0);

        assertEquals(2000, reportPl.getYear());
        assertEquals(1, reportPl.getQuarter());
        assertEquals(10, reportPl.getTax());
    }

    @Test
    public void when_call_searchByParams_without_quarter_then_return_result() {
        when(reportRepository.findAllByYear(anyInt())).thenReturn(List.of(Report.builder()
                .id(1)
                .year(2000)
                .quarter(1)
                .tax(10)
                .build()));

        ReportPl reportPl = service.searchByParams(2000, null).get(0);

        assertEquals(2000, reportPl.getYear());
        assertEquals(1, reportPl.getQuarter());
        assertEquals(10, reportPl.getTax());
    }

    @Test
    public void when_call_searchByParams_without_year_and_quarter_then_return_result() {
        when(reportRepository.findAll()).thenReturn(List.of(Report.builder()
                .id(1)
                .year(2000)
                .quarter(1)
                .tax(10)
                .build()));

        ReportPl reportPl = service.searchByParams(null, null).get(0);

        assertEquals(2000, reportPl.getYear());
        assertEquals(1, reportPl.getQuarter());
        assertEquals(10, reportPl.getTax());
    }

}
