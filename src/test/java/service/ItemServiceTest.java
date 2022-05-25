package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ItemRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemCategory;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemType;
import com.shepard1992.gmail.twoya_formochka.repository.specification.ItemSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
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
import static org.mockito.Mockito.when;

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

}
