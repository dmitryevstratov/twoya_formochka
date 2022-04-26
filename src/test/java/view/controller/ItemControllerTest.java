package view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ItemController;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import view.config.ViewTestConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(ViewTestConfig.class)
public class ItemControllerTest {

    @Autowired
    public ItemService service;

    @Autowired
    public ItemController controller;

    @Test
    public void test_when_call_getItemById_then_return_result() {
        when(service.getItemById(any())).thenReturn(ItemPl.builder()
                .id(1L)
                .build());

        ItemPl itemPl = controller.getItemById(1L);

        assertEquals(1L, itemPl.getId().longValue());
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(service.searchByParams(any())).thenReturn(List.of(ItemPl.builder()
                .id(1L)
                .build()));

        List<ItemPl> itemPlList = controller.searchByParams(1L, "", "", "", 10.0);

        assertEquals(1L, itemPlList.get(0).getId().longValue());
    }

}
