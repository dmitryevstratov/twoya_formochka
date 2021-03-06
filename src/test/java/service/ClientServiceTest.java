package service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.specification.ClientSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.view.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.ClientServiceTestConfig;
import service.config.MapperTestConfig;
import stubs.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import({
        MapperTestConfig.class,
        ClientServiceTestConfig.class
})
public class ClientServiceTest {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private DiscountRepository discountRepository;

    private final List<Order> orderList = new ArrayList<>();
    private final List<CreateOrderPl> createOrderPlList = new ArrayList<>();
    private final List<Discount> discountList = List.of(Discount.builder()
            .type(DiscountType.builder().build())
            .build());
    private final List<DiscountPl> discountPlSet = new ArrayList<>();
    private final Client stub = ClientStub.getStub(
            AddressStub.getStub(),
            orderList,
            discountList
    );
    private final ClientPl stubPl = ClientPlStub.getStub(
            AddressPlStub.getStub(),
            createOrderPlList,
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

        ClientPl clientPl = service.getClientById(1);

        assertEquals(stubPl.getId(), clientPl.getId());
    }

    @Test
    public void test_when_call_deleteClientById_then_return_success() {
        doNothing().when(repository).deleteById(any());

        service.deleteClientById(1);

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

    @Test
    public void test_when_call_getClientsWithDiscounts_then_return_result() {
        when(repository.findAll()).thenReturn(Collections.singletonList(stub));

        List<ClientPl> clients = service.getClientsWithDiscounts();

        assertNotNull(clients);
        assertEquals(stub.getId(), clients.get(0).getId());
    }

    @Test
    public void test_when_call_clientWithDiscountSearchByParams_then_return_result() {
        when(repository.findAll(any(ClientSpecification.class))).thenReturn(Collections.singletonList(stub));

        List<ClientPl> clients = service.clientWithDiscountSearchByParams(FilterPlStub.getStub());

        assertNotNull(clients);
        assertEquals(stub.getId(), clients.get(0).getId());
    }

    @Test
    public void test_when_call_editClientWithDiscount_then_return_result() {
        when(repository.findById(any())).thenReturn(Optional.of(Client.builder()
                .id(2)
                .discounts(new ArrayList<>())
                .build()));

        when(discountRepository.findByTypeNameAndValue(anyString(), anyInt())).thenReturn(Discount.builder()
                .id(3)
                .value(20)
                .type(DiscountType.builder()
                        .id(3)
                        .name("NY")
                        .build())
                .build());

        ClientPl clientPl = service.editClientWithDiscount(ClientWithDiscountPl.builder()
                .id(2)
                .discounts(List.of(DiscountPl.builder()
                        .id(3)
                        .value(20)
                        .type(DiscountTypePl.builder()
                                .id(3)
                                .name("NY")
                                .build())
                        .build()))
                .build());

        assertEquals(Integer.valueOf(2), clientPl.getId());
        assertEquals(Integer.valueOf(3), clientPl.getDiscounts().get(0).getId());
    }

}
