package service.config;

import com.shepard1992.gmail.twoya_formochka.service.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperTestConfig {

    @Bean
    public OrderMapper getOrderMapperBean() {
        return new OrderMapper();
    }

    @Bean
    public ItemMapper getItemMapperBean() {
        return new ItemMapper();
    }

    @Bean
    public ItemFilterMapper getItemFilterMapperBean() {
        return new ItemFilterMapper();
    }

    @Bean
    public DiscountMapper getDiscountMapperBean() {
        return new DiscountMapper();
    }

    @Bean
    public AddressMapper getAddressMapperBean() {
        return new AddressMapper();
    }

    @Bean
    public ClientMapper getClientMapperBean(DiscountMapper discountMapper) {
        return new ClientMapper(getAddressMapperBean(), discountMapper);
    }

    @Bean
    public FilterMapper getFilterMapperBean() {
        return new FilterMapper();
    }

    @Bean
    public ReportMapper getReportMapperBean() {
        return new ReportMapper();
    }

}
