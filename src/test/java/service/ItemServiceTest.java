package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ItemCategoryRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.ItemRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.ItemTypeRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemCategory;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemType;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.specification.ItemSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemCategoryPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemTypePl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.ItemServiceTestConfig;
import service.config.MapperTestConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import({
        MapperTestConfig.class,
        ItemServiceTestConfig.class
})
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    private static final Item itemStub = Item.builder()
            .id(1)
            .price(10.0)
            .size(7.5)
            .category(ItemCategory.builder()
                    .id(1)
                    .name("Категория")
                    .build())
            .type(ItemType.builder()
                    .id(4)
                    .name("Оттиск")
                    .build())
            .build();

    @Test
    public void test_when_call_getItemById_then_return_result() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(itemStub));

        ItemPl item = itemService.getItemById(1);

        assertEquals(1L, item.getId().longValue());
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(repository.findAll(any(ItemSpecification.class))).thenReturn(List.of(itemStub));

        List<ItemPl> itemPlList = itemService.searchByParams(ItemFilterPl.builder().build());

        assertEquals(1L, itemPlList.get(0).getId().longValue());
    }

    @Test
    public void test_when_call_getItems_then_return_result() {
        when(repository.findAll()).thenReturn(List.of(Item.builder()
                .id(1)
                .type(ItemType.builder().id(1).build())
                .category(ItemCategory.builder().id(1).build())
                .build()));

        ItemPl items = itemService.getItems().get(0);

        assertEquals(Integer.valueOf(1), items.getId());
    }

    @Test
    public void test_when_call_deleteItemById_then_return_result() {
        doNothing().when(repository).deleteById(anyInt());
        when(repository.findById(anyInt())).thenReturn(Optional.of(Item.builder()
                .id(1)
                .orders(List.of(Order.builder().build()))
                .build()));

        itemService.deleteItemById(1);

        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    public void test_when_call_searchItemCategory_then_return_result() {
        when(itemCategoryRepository.findAll()).thenReturn(List.of(ItemCategory.builder().id(7).build()));

        ItemCategoryPl categoryPl = itemService.searchItemCategory(null).get(0);

        assertEquals(Integer.valueOf(7), categoryPl.getId());
    }

    @Test
    public void test_when_searchItemType_then_return_result() {
        when(itemTypeRepository.findAll()).thenReturn(List.of(ItemType.builder().id(9).build()));

        ItemTypePl typePl = itemService.searchItemType(null).get(0);

        assertEquals(Integer.valueOf(9), typePl.getId());
    }

    @Test
    public void test_when_addItem_then_return_result() {
        when(repository.save(any())).thenReturn(Item.builder()
                .id(10)
                .type(ItemType.builder()
                        .id(1)
                        .name("afweqf")
                        .build())
                .category(ItemCategory.builder()
                        .id(1)
                        .name("qd")
                        .build())
                .build());
        when(itemTypeRepository.findByName(anyString())).thenReturn(ItemType.builder().build());
        when(itemCategoryRepository.findByName(anyString())).thenReturn(ItemCategory.builder().build());

        ItemPl itemPl = itemService.addItem(ItemPl.builder()
                .id(10)
                .type(ItemTypePl.builder()
                        .id(1)
                        .name("afweqf")
                        .build())
                .category(ItemCategoryPl.builder()
                        .id(1)
                        .name("qd")
                        .build())
                .build());

        assertEquals(Integer.valueOf(10), itemPl.getId());
    }

    @Test
    public void test_when_editItem_then_return_result() {
        when(repository.save(any())).thenReturn(Item.builder()
                .id(10)
                .type(ItemType.builder()
                        .id(1)
                        .name("afweqf")
                        .build())
                .category(ItemCategory.builder()
                        .id(1)
                        .name("qd")
                        .build())
                .build());
        when(itemTypeRepository.findByName(anyString())).thenReturn(ItemType.builder().build());
        when(itemCategoryRepository.findByName(anyString())).thenReturn(ItemCategory.builder().build());

        ItemPl itemPl = itemService.editItem(ItemPl.builder()
                .id(10)
                .type(ItemTypePl.builder()
                        .id(1)
                        .name("afweqf")
                        .build())
                .category(ItemCategoryPl.builder()
                        .id(1)
                        .name("qd")
                        .build())
                .build());

        assertEquals(Integer.valueOf(10), itemPl.getId());
    }

}
