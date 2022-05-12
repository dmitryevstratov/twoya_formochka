package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
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
public class DiscountRepositoryTest {

    @Autowired
    private DiscountRepository repository;

    @Before
    public void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void test_when_call_save_then_return_result() {
        Discount discount = repository.save(Discount.builder().build());

        assertNotNull(discount);
        assertNotNull(repository.findById(discount.getId()));
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        repository.save(Discount.builder().build());
        repository.save(Discount.builder().build());
        repository.save(Discount.builder().build());

        List<Discount> discountList = repository.findAll();

        assertEquals(3, discountList.size());
    }

    @Test
    public void test_when_call_findById_then_return_result() {
        repository.save(Discount.builder()
                .build());

        Discount discount = repository.findAll().get(0);

        assertNotNull(discount);
    }

    @Test
    public void test_when_call_deleteById_then_return_success() {
        Discount discount = repository.save(Discount.builder().build());

        repository.deleteById(discount.getId());

        assertEquals(0, repository.findAll().size());
    }

}