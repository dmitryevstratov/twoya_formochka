package service.config;

import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.service.OrderServiceImpl;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.OrderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class OrderServiceTestConfig {

    @Bean
    public OrderRepository getOrderRepositoryBean() {
        return mock(OrderRepository.class);
    }

    @Bean
    public OrderService getOrderServiceBean(OrderMapper mapper, FilterMapper filterMapper, OrderRepository repository) {
        return new OrderServiceImpl(mapper, filterMapper, repository);
    }

}
