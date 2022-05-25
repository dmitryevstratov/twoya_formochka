package view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.DiscountTypeService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.DiscountTypeController;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import view.config.ViewTestConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(ViewTestConfig.class)
public class DiscountTypeControllerTest {

    @Autowired
    public DiscountTypeService service;

    @Autowired
    public DiscountTypeController controller;

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(service.searchByParams(anyString())).thenReturn(List.of(DiscountTypePl.builder()
                .id(1)
                .build()));

        DiscountTypePl discountTypePl = controller.searchByParams("").get(0);

        assertEquals(Integer.valueOf(1), discountTypePl.getId());
    }

}
