package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.repository.model.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.model.Order;
import com.shepard1992.gmail.twoya_formochka.repository.specification.ClientSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.ClientServiceConfig;
import service.config.MapperConfig;
import view.stubs.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import({
        MapperConfig.class,
        ClientServiceConfig.class
})
public class ClientServiceTest {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientRepository repository;

    private final List<Order> orderList = new ArrayList<>();
    private final List<OrderPl> orderPlList = new ArrayList<>();
    private final Set<Discount> discountSet = new HashSet<>();
    private final Set<DiscountPl> discountPlSet = new HashSet<>();
    private final Client stub = ClientStub.getStub(
            AddressStub.getStub(),
            orderList,
            discountSet
    );
    private final ClientPl stubPl = ClientPlStub.getStub(
            AddressPlStub.getStub(),
            orderPlList,
            discountPlSet
    );

    @Test
    public void test_when_call_addClient_then_return_result() {

        when(repository.save(any())).thenReturn(stub);

        ClientPl clientPl = service.addClient(stubPl);

        assertEquals(stubPl.getId(), clientPl.getId());
    }

    @Test
    public void test_when_call_getClients_then_return_result() {
        when(repository.findAll()).thenReturn(Collections.singletonList(stub));

        List<ClientPl> clients = service.getClients();

        assertNotNull(clients);
        assertEquals(stub.getId(), clients.get(0).getId());
    }

    @Test
    public void test_when_call_getClientById_then_return_result() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(stub));

        ClientPl clientPl = service.getClientById(1L);

        assertEquals(stubPl.getId(), clientPl.getId());
    }

    @Test
    public void test_when_call_deleteClientById_then_return_success() {
        doNothing().when(repository).deleteById(any());

        service.deleteClientById(1L);

        verify(repository, times(1)).deleteById(any());
    }

    @Test
    public void test_when_call_editClient_then_return_result() {

        when(repository.save(any())).thenReturn(stub);

        ClientPl clientPl = service.editClient(stubPl);

        assertEquals(stubPl.getId(), clientPl.getId());
    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {
        when(repository.findAll(any(ClientSpecification.class))).thenReturn(Collections.singletonList(stub));

        List<ClientPl> clients = service.searchByParams(FilterPlStub.getStub());

        assertNotNull(clients);
        assertEquals(stub.getId(), clients.get(0).getId());
    }

}
