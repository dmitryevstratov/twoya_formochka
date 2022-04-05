package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.model.Filter;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import view.stubs.FilterPlStub;

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

}
