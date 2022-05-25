package service.config;

import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.ReportRepository;
import com.shepard1992.gmail.twoya_formochka.service.ReportServiceImpl;
import com.shepard1992.gmail.twoya_formochka.service.api.ReportService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ReportMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ReportServiceTestConfig {

    @Bean
    public ReportRepository getReportRepositoryBean() {
        return mock(ReportRepository.class);
    }

    @Bean
    public OrderRepository getOrderRepositoryBean() {
        return mock(OrderRepository.class);
    }

    @Bean
    public ReportService getReportServiceBean(ReportRepository reportRepository, OrderRepository orderRepository, ReportMapper reportMapper) {
        return new ReportServiceImpl(reportRepository, orderRepository, reportMapper);
    }

}
