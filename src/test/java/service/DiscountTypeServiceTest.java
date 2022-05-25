package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountTypeRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.DiscountServiceTestConfig;
import service.config.MapperTestConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import({
        MapperTestConfig.class,
        DiscountServiceTestConfig.class
})
public class DiscountTypeServiceTest {

    @Autowired
    private DiscountTypeService service;

    @Autowired
    private DiscountTypeRepository repository;

    @Before
    public void clear() {
        reset(repository);
    }

    @Test
    public void test_when_call_searchByParams_with_empty_type_then_return_result() {
        when(repository.findAll()).thenReturn(List.of(DiscountType.builder()
                .id(123)
                .build()));

        Integer id = service.searchByParams("").get(0).getId();

        assertEquals(Integer.valueOf(123), id);
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(repository.findAllByName(anyString())).thenReturn(List.of(DiscountType.builder()
                .id(123)
                .build()));

        Integer id = service.searchByParams("123123").get(0).getId();

        assertEquals(Integer.valueOf(123), id);
    }

}
