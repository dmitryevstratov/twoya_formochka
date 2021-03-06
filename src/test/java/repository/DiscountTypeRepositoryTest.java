package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountTypeRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.config.RepositoryTestConfig;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TwoyaFormochkaApplication.class,
        RepositoryTestConfig.class
})
public class DiscountTypeRepositoryTest {

    @Autowired
    private DiscountTypeRepository repository;

    @Test
    public void test_when_call_save_then_return_result() {
        DiscountType type = repository.save(DiscountType.builder().build());

        assertNotNull(type);
        assertNotNull(repository.findById(type.getId()));
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        DiscountType save1 = repository.save(DiscountType.builder().build());
        DiscountType save2 = repository.save(DiscountType.builder().build());
        DiscountType save3 = repository.save(DiscountType.builder().build());

        assertEquals(save1.getId(), repository.getById(save1.getId()).getId());
        assertEquals(save2.getId(), repository.getById(save2.getId()).getId());
        assertEquals(save3.getId(), repository.getById(save3.getId()).getId());
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
        Optional<DiscountType> first = repository.findAll().stream()
                .filter(tp -> tp.getId().equals(type.getId()))
                .findFirst();

        assertTrue(first.isEmpty());
    }

    @Test
    public void test_when_call_findByName_then_return_success() {
        DiscountType type = repository.save(DiscountType.builder()
                .name("NY")
                .build());

        DiscountType rs = repository.findByName("NY");

        assertEquals(type.getId(), rs.getId());
        assertEquals(type.getName(), rs.getName());
    }

    @Test
    public void test_when_call_findAllByName_then_return_success() {
        DiscountType type_1 = repository.save(DiscountType.builder()
                .name("NY")
                .build());
        DiscountType type_2 = repository.save(DiscountType.builder()
                .name("NY")
                .build());
        DiscountType type_3 = repository.save(DiscountType.builder()
                .name("NY")
                .build());
        List<DiscountType> rs = repository.findAllByName("NY");

        DiscountType rs_type_1 = rs.get(0);
        DiscountType rs_type_2 = rs.get(1);
        DiscountType rs_type_3 = rs.get(2);

        assertEquals(type_1.getId(), rs_type_1.getId());
        assertEquals(type_1.getName(), rs_type_1.getName());
        assertEquals(type_2.getId(), rs_type_2.getId());
        assertEquals(type_2.getName(), rs_type_2.getName());
        assertEquals(type_3.getId(), rs_type_3.getId());
        assertEquals(type_3.getName(), rs_type_3.getName());
    }

}
