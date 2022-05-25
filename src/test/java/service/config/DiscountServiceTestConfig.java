package service.config;

import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountTypeRepository;
import com.shepard1992.gmail.twoya_formochka.service.DiscountServiceImpl;
import com.shepard1992.gmail.twoya_formochka.service.DiscountTypeServiceImpl;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountService;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountTypeService;
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
    public DiscountTypeService getDiscountTypeServiceBean(DiscountTypeRepository discountTypeRepository) {
        return new DiscountTypeServiceImpl(discountTypeRepository);
    }

    @Bean
    public DiscountService getDiscountServiceBean(DiscountMapper mapper, DiscountTypeRepository discountTypeRepository, DiscountRepository repository) {
        return new DiscountServiceImpl(repository, discountTypeRepository, mapper);
    }

}
