package service.config;

import com.shepard1992.gmail.twoya_formochka.repository.api.ItemCategoryRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.ItemRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.ItemTypeRepository;
import com.shepard1992.gmail.twoya_formochka.service.ItemServiceImpl;
import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ItemFilterMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ItemMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ItemServiceTestConfig {

    @Bean
    public ItemRepository getItemRepositoryBean() {
        return mock(ItemRepository.class);
    }

    @Bean
    public ItemTypeRepository getItemTypeRepositoryBean() {
        return mock(ItemTypeRepository.class);
    }

    @Bean
    public ItemCategoryRepository getItemCategoryRepositoryBean() {
        return mock(ItemCategoryRepository.class);
    }

    @Bean
    public ItemService getItemServiceBean(ItemRepository repository, ItemTypeRepository typeRepository, ItemCategoryRepository categoryRepository, ItemFilterMapper filterMapper, ItemMapper itemMapper) {
        return new ItemServiceImpl(repository, typeRepository, categoryRepository, filterMapper, itemMapper);
    }

}
