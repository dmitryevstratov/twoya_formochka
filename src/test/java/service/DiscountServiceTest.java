package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import com.shepard1992.gmail.twoya_formochka.repository.specification.DiscountSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.DiscountServiceTestConfig;
import service.config.MapperTestConfig;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import({
        MapperTestConfig.class,
        DiscountServiceTestConfig.class
})
public class DiscountServiceTest {

    @Autowired
    private DiscountService service;

    @Autowired
    private DiscountRepository repository;

    @Before
    public void clear() {
        reset(repository);
    }

    @Test
    public void test_when_call_getDiscounts_then_return_result() {
        when(repository.findAll()).thenReturn(List.of(Discount.builder()
                .id(123)
                .type(DiscountType.builder()
                        .id(2)
                        .name("DDDD")
                        .build())
                .build()));

        Integer id = service.getDiscounts().get(0).getId();

        assertEquals(Integer.valueOf(123), id);
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(repository.findAll(any(DiscountSpecification.class))).thenReturn(
                Collections.singletonList(Discount.builder()
                        .id(123)
                        .type(DiscountType.builder()
                                .id(2)
                                .name("DDDD")
                                .build())
                        .build()));

        Integer id = service.searchByParams(1, "").get(0).getId();

        assertEquals(Integer.valueOf(123), id);
    }

}
