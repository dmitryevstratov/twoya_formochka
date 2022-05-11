package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.api.ItemRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.config.RepositoryTestConfig;
import stubs.ItemStub;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TwoyaFormochkaApplication.class,
        RepositoryTestConfig.class
})
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @Before
    public void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void test_when_call_save_then_return_result() {
        Item item = repository.save(ItemStub.getStub());

        assertNotNull(item);
        assertNotNull(repository.findById(item.getId()));
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        repository.save(Item.builder().build());
        repository.save(Item.builder().build());
        repository.save(Item.builder().build());

        List<Item> itemList = repository.findAll();

        assertEquals(3, itemList.size());
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

        assertEquals(0, repository.findAll().size());
    }

}
