package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.dto.FilterItem;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ItemFilterMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class FilterItemMapperTest {

    @Autowired
    private ItemFilterMapper itemFilterMapper;

    @Test
    public void test_mapperToFilter() {
        ItemFilterPl itemFilterPl = ItemFilterPl.builder()
                .id(2L)
                .name("Снежинка")
                .type("Вырубка")
                .category("НГ")
                .size(20.1)
                .build();

        FilterItem filterItem = itemFilterMapper.mapperToFilter(itemFilterPl);

        assertEquals(itemFilterPl.getId(), filterItem.getId());
        assertEquals(itemFilterPl.getName(), filterItem.getName());
        assertEquals(itemFilterPl.getSize(), filterItem.getSize());
        assertEquals(itemFilterPl.getType(), filterItem.getType());
        assertEquals(itemFilterPl.getCategory(), filterItem.getCategory());
    }

}
