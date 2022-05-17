package view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.DiscountService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.DiscountController;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import view.config.ViewTestConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import(ViewTestConfig.class)
public class DiscountControllerTest {

    @Autowired
    public DiscountService service;

    @Autowired
    public DiscountController controller;

    @Test
    public void test_when_call_getDiscounts_then_return_result() {
        when(service.getDiscounts()).thenReturn(List.of(DiscountPl.builder()
                .id(4)
                .build()));

        DiscountPl discountPl = controller.getDiscounts().get(0);

        assertEquals(Integer.valueOf(4), discountPl.getId());
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(service.searchByParams(anyInt(), anyString())).thenReturn(List.of(DiscountPl.builder()
                .id(1)
                .build()));

        DiscountPl discountPl = controller.searchByParams(1, "").get(0);

        assertEquals(Integer.valueOf(1), discountPl.getId());
    }

    @Test
    public void test_when_call_getDiscountById_then_return_result() {
        when(service.getDiscountById(any())).thenReturn(DiscountPl.builder()
                .id(23)
                .type(DiscountTypePl.builder().build())
                .build());

        Integer id = controller.getDiscountById(2).getId();

        assertEquals(Integer.valueOf(23), id);
    }

    @Test
    public void test_when_call_addDiscount_then_return_result() {
        when(service.addDiscount(any())).thenReturn(DiscountPl.builder()
                .id(23)
                .type(DiscountTypePl.builder().build())
                .build());

        Integer id = controller.addDiscount(DiscountPl.builder()
                .type(DiscountTypePl.builder().build())
                .build()).getId();

        assertEquals(Integer.valueOf(23), id);
    }

    @Test
    public void test_when_call_editDiscount_then_return_result() {
        when(service.editDiscount(any())).thenReturn(DiscountPl.builder()
                .id(23)
                .type(DiscountTypePl.builder().build())
                .build());

        Integer id = controller.editDiscount(DiscountPl.builder()
                .type(DiscountTypePl.builder().build())
                .build()).getId();

        assertEquals(Integer.valueOf(23), id);
    }

    @Test
    public void test_when_call_deleteDiscountById_then_return_success() {
        doNothing().when(service).deleteDiscountById(anyInt());

        controller.deleteDiscountById(1);

        verify(service, times(1)).deleteDiscountById(any());
    }

}
