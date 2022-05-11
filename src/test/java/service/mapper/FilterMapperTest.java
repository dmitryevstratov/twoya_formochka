package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.dto.Filter;
import com.shepard1992.gmail.twoya_formochka.repository.specification.FilterOrder;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import stubs.FilterOrderPlStub;
import stubs.FilterPlStub;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class FilterMapperTest {

    @Autowired
    private FilterMapper filterMapper;

    @Test
    public void test_mapperToFilter() {
        Filter filter = filterMapper.mapperToFilter(FilterPlStub.getStub());

        assertEquals(FilterPlStub.getStub().getId(), filter.getId());
        assertEquals(FilterPlStub.getStub().getFirstName(), filter.getFirstName());
        assertEquals(FilterPlStub.getStub().getLastName(), filter.getLastName());
        assertEquals(FilterPlStub.getStub().getSecondName(), filter.getSecondName());
        assertEquals(FilterPlStub.getStub().getBirthday(), filter.getBirthday());
        assertEquals(FilterPlStub.getStub().getEmail(), filter.getEmail());
        assertEquals(FilterPlStub.getStub().getTelephone(), filter.getTelephone());
    }

    @Test
    public void test_mapperToOrderFilter() {
        FilterOrder filterOrder = filterMapper.mapperToOrderFilter(FilterOrderPlStub.getStub());

        assertEquals(FilterOrderPlStub.getStub().getId(), filterOrder.getId());
        assertEquals(FilterOrderPlStub.getStub().getFirstName(), filterOrder.getFirstName());
        assertEquals(FilterOrderPlStub.getStub().getLastName(), filterOrder.getLastName());
        assertEquals(FilterOrderPlStub.getStub().getDateCreate(), filterOrder.getDateCreate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        assertEquals(FilterOrderPlStub.getStub().getDateClosed(), filterOrder.getDateClosed().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        assertEquals(FilterOrderPlStub.getStub().getPriceMax(), filterOrder.getPriceMax());
        assertEquals(FilterOrderPlStub.getStub().getPriceMin(), filterOrder.getPriceMin());
        assertEquals(FilterOrderPlStub.getStub().getSelectedStatus(), filterOrder.getSelectedStatus());
        assertEquals(FilterOrderPlStub.getStub().getCount(), filterOrder.getCount());
    }

}
