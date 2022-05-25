package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.specification.DiscountTypeSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountService;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;
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
import java.util.Optional;

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
        when(repository.findAll(any(DiscountTypeSpecification.class))).thenReturn(
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

    @Test
    public void test_when_call_getDiscountById_then_return_result() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(Discount.builder()
                .id(7)
                .type(DiscountType.builder().build())
                .build()));

        Integer id = service.getDiscountById(4).getId();

        assertEquals(Integer.valueOf(7), id);
    }

    @Test
    public void test_when_call_addDiscount_then_return_result() {
        when(repository.save(any())).thenReturn(Discount.builder()
                .id(1234)
                .type(DiscountType.builder()
                        .id(1)
                        .build())
                .build());

        Integer id = service.addDiscount(DiscountPl.builder()
                .id(1234)
                .type(DiscountTypePl.builder()
                        .id(1)
                        .build())
                .build()).getId();

        assertEquals(Integer.valueOf(1234), id);
    }

    @Test
    public void test_when_call_editDiscount_then_return_result() {
        when(repository.save(any())).thenReturn(Discount.builder()
                .id(1234)
                .type(DiscountType.builder()
                        .id(1)
                        .build())
                .build());

        Integer id = service.editDiscount(DiscountPl.builder()
                .id(1)
                .type(DiscountTypePl.builder()
                        .id(1)
                        .build())
                .build()).getId();

        assertEquals(Integer.valueOf(1234), id);
    }

    @Test
    public void test_when_call_deleteDiscountById_then_return_success() {
        doNothing().when(repository).deleteById(anyInt());
        when(repository.findById(anyInt())).thenReturn(Optional.of(Discount.builder()
                .id(1)
                .type(DiscountType.builder()
                        .id(1)
                        .build())
                .clients(List.of(
                        Client.builder().build()
                ))
                .orders(List.of(Order.builder().build()))
                .build()));

        service.deleteDiscountById(1);

        verify(repository, times(1)).deleteById(any());
    }

}
