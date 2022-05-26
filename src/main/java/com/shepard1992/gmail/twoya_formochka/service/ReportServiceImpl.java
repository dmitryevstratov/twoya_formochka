package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Report;
import com.shepard1992.gmail.twoya_formochka.repository.api.ReportRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.service.api.ReportService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ReportMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateReportPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ReportPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final OrderRepository orderRepository;
    private final ReportMapper mapper;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, OrderRepository orderRepository, ReportMapper mapper) {
        this.reportRepository = reportRepository;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public ReportPl addReport(CreateReportPl createReportPl) {
        List<Order> orderList = new ArrayList<>();
        Report report = reportRepository.findByYearAndQuarter(createReportPl.getYear(), createReportPl.getQuarter());

        if (report == null) {
            report = Report.builder()
                    .year(createReportPl.getYear())
                    .quarter(createReportPl.getQuarter())
                    .build();
        }

        double income;
        double sumForTax;
        double clearIncome;
        int tax = createReportPl.getTax();

        if (createReportPl.getQuarter() == 1) {
            addOrdersByQuarter(orderList, createReportPl.getYear(), new int[]{1, 2, 3});
        }
        if (createReportPl.getQuarter() == 2) {
            addOrdersByQuarter(orderList, createReportPl.getYear(), new int[]{4, 5, 6});
        }
        if (createReportPl.getQuarter() == 3) {
            addOrdersByQuarter(orderList, createReportPl.getYear(), new int[]{7, 8, 9});
        }
        if (createReportPl.getQuarter() == 4) {
            addOrdersByQuarter(orderList, createReportPl.getYear(), new int[]{10, 11, 12});
        }

        income = orderList.stream().mapToDouble(Order::getTotalPrice).sum();
        sumForTax = income * ((double) tax / 100);
        clearIncome = income - sumForTax;

        report.setIncome(income);
        report.setSumForTax(sumForTax);
        report.setClearIncome(clearIncome);
        report.setTax(tax);

        return mapper.mapperToReportPl(reportRepository.save(report));
    }

    @Override
    public List<ReportPl> searchByParams(Integer year, Integer quarter) {
        if (StringUtils.isEmpty(year) && StringUtils.isEmpty(quarter)) {
            return reportRepository.findAll().stream()
                    .map(mapper::mapperToReportPl)
                    .sorted(Comparator.comparing(ReportPl::getYear).thenComparing(ReportPl::getQuarter))
                    .collect(Collectors.toList());
        }
        if (StringUtils.isEmpty(quarter)) {
            return reportRepository.findAllByYear(year).stream()
                    .map(mapper::mapperToReportPl)
                    .sorted(Comparator.comparing(ReportPl::getYear).thenComparing(ReportPl::getQuarter))
                    .collect(Collectors.toList());
        }
        return Collections.singletonList(mapper.mapperToReportPl(reportRepository.findByYearAndQuarter(year, quarter)));
    }

    private void addOrdersByQuarter(List<Order> orderList, int year, int[] months) {
        orderList.addAll(orderRepository.getOrdersByYearAndMonth(year, covertNumMonthToString(months[0])));
        orderList.addAll(orderRepository.getOrdersByYearAndMonth(year, covertNumMonthToString(months[1])));
        orderList.addAll(orderRepository.getOrdersByYearAndMonth(year, covertNumMonthToString(months[2])));
    }

    private String covertNumMonthToString(int numMonth) {
        return (numMonth < 10) ? "0" + numMonth : numMonth + "";
    }

}
