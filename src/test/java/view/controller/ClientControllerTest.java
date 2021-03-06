package view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ClientController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import stubs.AddressPlStub;
import stubs.ClientPlStub;
import stubs.DiscountPlStub;
import view.config.ViewTestConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import(ViewTestConfig.class)
public class ClientControllerTest {

    @Autowired
    public ClientController clientController;

    @Qualifier("getClientControllerBean")
    @Autowired
    public ModelAndViewController modelAndViewController;

    @Autowired
    public ClientService clientService;

    @Test
    public void test_when_call_getClients_then_return_result() {
        List<ClientPl> clientPlList = new ArrayList<>();
        List<DiscountPl> discountPlList = new ArrayList<>();
        List<CreateOrderPl> createOrderPlList = new ArrayList<>();

        createOrderPlList.add(CreateOrderPl.builder()
                .idClient(1)
                .price(10.2)
                .idDiscount(1)
                .items(List.of(ItemsOrderPl.builder().id(7).count(2).build()))
                .build());
        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1)
                .name("Новый Год")
                .build()));
        clientPlList.add(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                createOrderPlList,
                discountPlList
        ));

        when(clientService.getClients()).thenReturn(clientPlList);
        List<ClientPl> clients = clientController.getClients();

        ClientPl clientPl = clients.get(0);
        AddressPl address = clientPl.getAddress();
        CreateOrderPl createOrderPl = clientPl.getOrders().get(0);

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals("01.02.1990", clientPl.getBirthday());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1)
                        .name("Новый Год")
                        .build())
                .build()));

        assertEquals(Integer.valueOf(1), createOrderPl.getIdDiscount());
        assertEquals(Integer.valueOf(1), createOrderPl.getIdClient());
        assertEquals(Double.valueOf(10.2), createOrderPl.getPrice());
        assertEquals(Integer.valueOf(7), createOrderPl.getItems().get(0).getId());
        assertEquals(Integer.valueOf(2), createOrderPl.getItems().get(0).getCount());

    }

    @Test
    public void test_when_call_getClientById_then_return_result() {
        List<DiscountPl> discountPlList = new ArrayList<>();
        List<CreateOrderPl> createOrderPlList = new ArrayList<>();

        createOrderPlList.add(CreateOrderPl.builder()
                .idClient(1)
                .price(10.2)
                .idDiscount(1)
                .items(List.of(ItemsOrderPl.builder().id(7).count(2).build()))
                .build());
        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1)
                .name("Новый Год")
                .build()));

        when(clientService.getClientById(any())).thenReturn(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                createOrderPlList,
                discountPlList
        ));

        ClientPl clientPl = clientController.getClientById(1);
        CreateOrderPl createOrderPl = clientPl.getOrders().get(0);
        AddressPl address = clientPl.getAddress();

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals("01.02.1990", clientPl.getBirthday());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1)
                        .name("Новый Год")
                        .build())
                .build()));

        assertEquals(Integer.valueOf(1), createOrderPl.getIdDiscount());
        assertEquals(Integer.valueOf(1), createOrderPl.getIdClient());
        assertEquals(Double.valueOf(10.2), createOrderPl.getPrice());
        assertEquals(Integer.valueOf(7), createOrderPl.getItems().get(0).getId());
        assertEquals(Integer.valueOf(2), createOrderPl.getItems().get(0).getCount());

    }

    @Test
    public void test_when_call_addClient_then_return_result() {

        List<DiscountPl> discountPlList = new ArrayList<>();
        List<CreateOrderPl> createOrderPlList = new ArrayList<>();

        createOrderPlList.add(CreateOrderPl.builder()
                .idClient(1)
                .price(10.2)
                .idDiscount(1)
                .items(List.of(ItemsOrderPl.builder().id(7).count(2).build()))
                .build());
        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1)
                .name("Новый Год")
                .build()));

        when(clientService.addClient(any())).thenReturn(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                createOrderPlList,
                discountPlList
        ));

        ClientPl clientPl = clientController.addClient(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                createOrderPlList,
                discountPlList
        ));

        CreateOrderPl createOrderPl = clientPl.getOrders().get(0);
        AddressPl address = clientPl.getAddress();

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals("01.02.1990", clientPl.getBirthday());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1)
                        .name("Новый Год")
                        .build())
                .build()));

        assertEquals(Integer.valueOf(1), createOrderPl.getIdDiscount());
        assertEquals(Integer.valueOf(1), createOrderPl.getIdClient());
        assertEquals(Double.valueOf(10.2), createOrderPl.getPrice());
        assertEquals(Integer.valueOf(7), createOrderPl.getItems().get(0).getId());
        assertEquals(Integer.valueOf(2), createOrderPl.getItems().get(0).getCount());

    }

    @Test
    public void test_when_call_editClient_then_return_result() {

        List<DiscountPl> discountPlList = new ArrayList<>();
        List<CreateOrderPl> createOrderPlList = new ArrayList<>();

        createOrderPlList.add(CreateOrderPl.builder()
                .idClient(1)
                .price(10.2)
                .idDiscount(1)
                .items(List.of(ItemsOrderPl.builder().id(7).count(2).build()))
                .build());
        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1)
                .name("Новый Год")
                .build()));

        when(clientService.editClient(any())).thenReturn(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                createOrderPlList,
                discountPlList
        ));

        ClientPl clientPl = clientController.editClient(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                createOrderPlList,
                discountPlList
        ));

        AddressPl address = clientPl.getAddress();
        CreateOrderPl createOrderPl = clientPl.getOrders().get(0);

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals("01.02.1990", clientPl.getBirthday());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1)
                        .name("Новый Год")
                        .build())
                .build()));

        assertEquals(Integer.valueOf(1), createOrderPl.getIdDiscount());
        assertEquals(Integer.valueOf(1), createOrderPl.getIdClient());
        assertEquals(Double.valueOf(10.2), createOrderPl.getPrice());
        assertEquals(Integer.valueOf(7), createOrderPl.getItems().get(0).getId());
        assertEquals(Integer.valueOf(2), createOrderPl.getItems().get(0).getCount());

    }

    @Test
    public void test_when_call_deleteClientById_then_return_result() {

        doNothing().when(clientService).deleteClientById(any());

        clientController.deleteClientById(1);

        verify(clientService, times(1)).deleteClientById(any());

    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {

        List<ClientPl> clientPlList = new ArrayList<>();
        List<DiscountPl> discountPlList = new ArrayList<>();
        List<CreateOrderPl> createOrderPlList = new ArrayList<>();

        createOrderPlList.add(CreateOrderPl.builder()
                .idClient(1)
                .price(10.2)
                .idDiscount(1)
                .items(List.of(ItemsOrderPl.builder().id(7).count(2).build()))
                .build());
        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1)
                .name("Новый Год")
                .build()));
        clientPlList.add(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                createOrderPlList,
                discountPlList
        ));

        when(clientService.searchByParams(any())).thenReturn(clientPlList);
        List<ClientPl> clients = clientController.searchByParams(
                1,
                "",
                "",
                "",
                "",
                "",
                ""
        );

        ClientPl clientPl = clients.get(0);
        AddressPl address = clientPl.getAddress();
        CreateOrderPl createOrderPl = clientPl.getOrders().get(0);

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals("01.02.1990", clientPl.getBirthday());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1)
                        .name("Новый Год")
                        .build())
                .build()));

        assertEquals(Integer.valueOf(1), createOrderPl.getIdDiscount());
        assertEquals(Integer.valueOf(1), createOrderPl.getIdClient());
        assertEquals(Double.valueOf(10.2), createOrderPl.getPrice());
        assertEquals(Integer.valueOf(7), createOrderPl.getItems().get(0).getId());
        assertEquals(Integer.valueOf(2), createOrderPl.getItems().get(0).getCount());

    }

    @Test
    public void test_when_call_getView_then_return_result() {

        assertNotNull(modelAndViewController.getView(new Model() {

            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }

        }));

    }

    @Test
    public void test_when_call_getClientsWithDiscounts_then_return_result() {
        when(clientService.getClientsWithDiscounts()).thenReturn(List.of(ClientPl.builder()
                .id(222)
                .build()));

        ClientPl clientPl = clientController.getClientsWithDiscounts().get(0);

        assertEquals(Integer.valueOf(222), clientPl.getId());
    }

    @Test
    public void test_when_call_searchByParams_by_discounts_then_return_result() {

        when(clientService.clientWithDiscountSearchByParams(any())).thenReturn(List.of(ClientPl.builder()
                .id(5)
                .build()));

        ClientPl client = clientController.searchByParams(
                1, "", "", ""
        ).get(0);

        assertEquals(Integer.valueOf(5), client.getId());

    }

    @Test
    public void test_when_call_editClientWithDiscount_then_return_result() {
        when(clientService.editClientWithDiscount(any())).thenReturn(ClientPl.builder()
                .id(45)
                .discounts(List.of(DiscountPl.builder()
                        .value(22)
                        .type(DiscountTypePl.builder()
                                .name("HB")
                                .build())
                        .build()))
                .build());

        ClientPl clientPl = clientController.editClientWithDiscount(ClientWithDiscountPl.builder().build());

        assertEquals(Integer.valueOf(45), clientPl.getId());
        assertEquals(Integer.valueOf(22), clientPl.getDiscounts().get(0).getValue());
        assertEquals("HB", clientPl.getDiscounts().get(0).getType().getName());
    }


}
