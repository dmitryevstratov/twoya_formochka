package view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ItemController;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemCategoryPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemTypePl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import view.config.ViewTestConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
                .id(1)
                .build());

        ItemPl itemPl = controller.getItemById(1);

        assertEquals(1L, itemPl.getId().longValue());
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(service.searchByParams(any())).thenReturn(List.of(ItemPl.builder()
                .id(1)
                .build()));

        List<ItemPl> itemPlList = controller.searchByParams(1, "", "", "", 10.0);

        assertEquals(1L, itemPlList.get(0).getId().longValue());
    }

    @Test
    public void test_when_call_getItems_then_return_result() {
        when(service.getItems()).thenReturn(List.of(ItemPl.builder().id(1).build()));

        ItemPl items = controller.getItems().get(0);

        assertEquals(Integer.valueOf(1), items.getId());
    }

    @Test
    public void test_when_call_deleteItemById_then_return_result() {
        doNothing().when(service).deleteItemById(anyInt());

        controller.deleteItemById(1);

        verify(service, times(1)).deleteItemById(anyInt());
    }

    @Test
    public void test_when_call_searchItemCategory_then_return_result() {
        when(service.searchItemCategory(anyString())).thenReturn(List.of(ItemCategoryPl.builder().id(7).build()));

        ItemCategoryPl categoryPl = controller.searchItemCategory("aacac").get(0);

        assertEquals(Integer.valueOf(7), categoryPl.getId());
    }

    @Test
    public void test_when_searchItemType_then_return_result() {
        when(service.searchItemType(anyString())).thenReturn(List.of(ItemTypePl.builder().id(9).build()));

        ItemTypePl typePl = controller.searchItemType("1112").get(0);

        assertEquals(Integer.valueOf(9), typePl.getId());
    }

    @Test
    public void test_when_addItem_then_return_result() {
        when(service.addItem(any())).thenReturn(ItemPl.builder().id(10).build());

        ItemPl itemPl = controller.addItem(ItemPl.builder().build());

        assertEquals(Integer.valueOf(10), itemPl.getId());
    }

    @Test
    public void test_when_editItem_then_return_result() {
        when(service.editItem(any())).thenReturn(ItemPl.builder().id(10).build());

        ItemPl itemPl = controller.editItem(ItemPl.builder().build());

        assertEquals(Integer.valueOf(10), itemPl.getId());
    }

}
