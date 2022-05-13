package service.config;

import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountTypeRepository;
import com.shepard1992.gmail.twoya_formochka.service.DiscountServiceImpl;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.DiscountMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class DiscountServiceTestConfig {

    @Bean
    public DiscountRepository getDiscountRepositoryBean() {
        return mock(DiscountRepository.class);
    }

    @Bean
    public DiscountTypeRepository getDiscountTypeRepositoryBean() {
        return mock(DiscountTypeRepository.class);
    }

    @Bean
    public DiscountService getDiscountServiceBean(DiscountMapper mapper, DiscountRepository repository) {
        return new DiscountServiceImpl(repository, mapper);
    }

}
