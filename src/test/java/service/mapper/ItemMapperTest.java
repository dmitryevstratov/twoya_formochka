package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemCategory;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemType;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ItemMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void test_mapperToItemPl() {
        Item item = Item.builder()
                .id(1L)
                .category(ItemCategory.builder()
                        .id(2L)
                        .name("Техника")
                        .build())
                .name("Машинка")
                .price(10.5)
                .type(ItemType.builder()
                        .id(5L)
                        .name("Вырубка")
                        .build())
                .build();

        ItemPl itemPl = itemMapper.mapperToItemPl(item);

        assertEquals(item.getId(), itemPl.getId());
        assertEquals(item.getCategory().getId(), itemPl.getCategory().getId());
        assertEquals(item.getCategory().getName(), itemPl.getCategory().getName());
        assertEquals(item.getType().getId(), itemPl.getType().getId());
        assertEquals(item.getType().getName(), itemPl.getType().getName());
        assertEquals(item.getName(), itemPl.getName());
        assertEquals(item.getPrice(), itemPl.getPrice());
    }

}
