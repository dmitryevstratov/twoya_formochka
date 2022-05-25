package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.api.AddressRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Address;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.config.RepositoryTestConfig;
import stubs.AddressStub;
import stubs.ClientStub;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TwoyaFormochkaApplication.class,
        RepositoryTestConfig.class
})
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Before
    public void cleanDB() {
        repository.deleteAll();
    }

    private final List<Order> orderList = new ArrayList<>();
    private final List<Discount> discountList = new ArrayList<>();
    private final Client stub = ClientStub.getStub(
            AddressStub.getStub(),
            orderList,
            discountList
    );

    @Test
    public void test_when_call_save_then_return_result() {
        Client clientSaved = repository.save(stub);
        Optional<Address> address = addressRepository.findById(clientSaved.getId());

        assertNotNull(address);
        assertNotNull(clientSaved);
        assertEquals(repository.findById(clientSaved.getId()).get().getId(), clientSaved.getId());
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        repository.save(stub);
        List<Client> clientList = repository.findAll();

        assertEquals(1, clientList.size());
    }

    @Test
    public void test_when_call_findById_then_return_result() {
        Client clientSaved = repository.save(stub);

        assertEquals(clientSaved.getId(), repository.findById(clientSaved.getId()).get().getId());
    }

    @Test
    public void test_when_call_deleteById_then_return_success() {
        Client clientSaved = repository.save(stub);
        repository.deleteById(clientSaved.getId());

        assertEquals(0, repository.findAll().size());
    }

}
