package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountTypeRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.config.RepositoryTestConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TwoyaFormochkaApplication.class,
        RepositoryTestConfig.class
})
public class DiscountTypeRepositoryTest {

    @Autowired
    private DiscountTypeRepository repository;

    @Before
    public void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void test_when_call_save_then_return_result() {
        DiscountType type = repository.save(DiscountType.builder().build());

        assertNotNull(type);
        assertNotNull(repository.findById(type.getId()));
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        repository.save(DiscountType.builder().build());
        repository.save(DiscountType.builder().build());
        repository.save(DiscountType.builder().build());

        List<DiscountType> typeList = repository.findAll();

        assertEquals(3, typeList.size());
    }

    @Test
    public void test_when_call_findById_then_return_result() {
        repository.save(DiscountType.builder()
                .build());

        DiscountType type = repository.findAll().get(0);

        assertNotNull(type);
    }

    @Test
    public void test_when_call_deleteById_then_return_success() {
        DiscountType type = repository.save(DiscountType.builder().build());

        repository.deleteById(type.getId());

        assertEquals(0, repository.findAll().size());
    }

}