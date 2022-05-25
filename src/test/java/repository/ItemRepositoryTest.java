package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.api.ItemRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.config.RepositoryTestConfig;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TwoyaFormochkaApplication.class,
        RepositoryTestConfig.class
})
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @Test
    public void test_when_call_save_then_return_result() {
        Item item = repository.save(Item.builder().build());

        assertNotNull(item);
        assertNotNull(repository.findById(item.getId()));
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        Item save1 = repository.save(Item.builder().build());
        Item save2 = repository.save(Item.builder().build());
        Item save3 = repository.save(Item.builder().build());

        assertEquals(save1.getId(), repository.getById(save1.getId()).getId());
        assertEquals(save2.getId(), repository.getById(save2.getId()).getId());
        assertEquals(save3.getId(), repository.getById(save3.getId()).getId());
    }

    @Test
    public void test_when_call_findById_then_return_result() {
        repository.save(Item.builder()
                .build());

        Item item = repository.findAll().get(0);

        assertNotNull(item);
    }

    @Test
    public void test_when_call_deleteById_then_return_success() {
        Item item = repository.save(Item.builder().build());

        repository.deleteById(item.getId());
        Optional<Item> first = repository.findAll().stream()
                .filter(it -> it.getId().equals(item.getId()))
                .findFirst();

        assertTrue(first.isEmpty());
    }

}
