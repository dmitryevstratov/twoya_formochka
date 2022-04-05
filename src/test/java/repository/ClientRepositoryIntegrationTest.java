package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.repository.model.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import repository.config.ClientRepositoryTestConfig;
import view.stubs.AddressStub;
import view.stubs.ClientStub;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TwoyaFormochkaApplication.class,
        ClientRepositoryTestConfig.class
})
@DirtiesContext
public class ClientRepositoryIntegrationTest {

    @Autowired
    private ClientRepository repository;

    private final List<Order> orderList = new ArrayList<>();
    private final Set<Discount> discountSet = new HashSet<>();
    private final Client stub = ClientStub.getStub(
            AddressStub.getStub(),
            orderList,
            discountSet
    );

    @Test
    public void test_when_call_save_then_return_result() {
        Client clientSaved = repository.save(stub);

        assertNotNull(clientSaved);
        assertEquals(repository.findById(clientSaved.getId()).get().getId(), clientSaved.getId());
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        repository.deleteAll();
        repository.save(stub);
        List<Client> clientList = repository.findAll();

        assertEquals(1, clientList.size());
    }

    @Test
    public void test_when_call_findById_then_return_result() {
        repository.deleteAll();
        Client clientSaved = repository.save(stub);

        assertEquals(clientSaved.getId(), repository.findById(clientSaved.getId()).get().getId());
    }

    @Test
    public void test_when_call_deleteById_then_return_success() {
        repository.deleteAll();
        Client clientSaved = repository.save(stub);
        repository.deleteById(clientSaved.getId());

        assertEquals(0, repository.findAll().size());
    }

}
