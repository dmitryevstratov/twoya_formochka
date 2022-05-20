package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.config.RepositoryTestConfig;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TwoyaFormochkaApplication.class,
        RepositoryTestConfig.class
})
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Before
    public void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void test_when_call_save_then_return_result() {
        Order order = repository.save(Order.builder().build());

        assertNotNull(order);
        assertNotNull(repository.findById(order.getId()));
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        repository.save(Order.builder().build());
        repository.save(Order.builder().build());
        repository.save(Order.builder().build());

        List<Order> orderList = repository.findAll();

        assertEquals(3, orderList.size());
    }

    @Test
    public void test_when_call_findById_then_return_result() {
        repository.save(Order.builder()
                .build());

        Order order = repository.findAll().get(0);

        assertNotNull(order);
    }

    @Test
    public void test_when_call_deleteById_then_return_success() {
        Order order = repository.save(Order.builder().build());

        repository.deleteById(order.getId());

        assertEquals(0, repository.findAll().size());
    }

}
