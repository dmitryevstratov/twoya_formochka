package view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ClientController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import view.config.ViewConfig;
import view.stubs.AddressPlStub;
import view.stubs.ClientPlStub;
import view.stubs.DiscountPlStub;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import(ViewConfig.class)
public class ClientControllerTest {

    @Autowired
    public ClientController clientController;

    @Autowired
    public ModelAndViewController modelAndViewController;

    @Autowired
    public ClientService clientService;

    @Test
    public void test_when_call_getClients_then_return_result() {
        List<ClientPl> clientPlList = new ArrayList<>();
        Set<DiscountPl> discountPlList = new HashSet<>();
        List<OrderPl> orderPlList = new ArrayList<>();

        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1L)
                .name("Новый Год")
                .build()));
        clientPlList.add(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                orderPlList,
                discountPlList
        ));

        when(clientService.getClients()).thenReturn(clientPlList);
        List<ClientPl> clients = clientController.getClients();

        ClientPl clientPl = clients.get(0);
        AddressPl address = clientPl.getAddress();

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals(new Date().toString(), clientPl.getBirthday().toString());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1L)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1L)
                        .name("Новый Год")
                        .build())
                .build()));

        //ToDo write test for orders

    }

    @Test
    public void test_when_call_getClientById_then_return_result() {
        Set<DiscountPl> discountPlList = new HashSet<>();
        List<OrderPl> orderPlList = new ArrayList<>();

        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1L)
                .name("Новый Год")
                .build()));

        when(clientService.getClientById(any())).thenReturn(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                orderPlList,
                discountPlList
        ));

        ClientPl clientPl = clientController.getClientById(1L);

        AddressPl address = clientPl.getAddress();

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals(new Date().toString(), clientPl.getBirthday().toString());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1L)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1L)
                        .name("Новый Год")
                        .build())
                .build()));

        //ToDo write test for orders

    }

    @Test
    public void test_when_call_addClient_then_return_result() {

        Set<DiscountPl> discountPlList = new HashSet<>();
        List<OrderPl> orderPlList = new ArrayList<>();

        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1L)
                .name("Новый Год")
                .build()));

        when(clientService.addClient(any())).thenReturn(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                orderPlList,
                discountPlList
        ));

        ClientPl clientPl = clientController.addClient(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                orderPlList,
                discountPlList
        ));

        AddressPl address = clientPl.getAddress();

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals(new Date().toString(), clientPl.getBirthday().toString());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1L)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1L)
                        .name("Новый Год")
                        .build())
                .build()));

        //ToDo write test for orders

    }

    @Test
    public void test_when_call_editClient_then_return_result() {

        Set<DiscountPl> discountPlList = new HashSet<>();
        List<OrderPl> orderPlList = new ArrayList<>();

        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1L)
                .name("Новый Год")
                .build()));

        when(clientService.editClient(any())).thenReturn(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                orderPlList,
                discountPlList
        ));

        ClientPl clientPl = clientController.editClient(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                orderPlList,
                discountPlList
        ));

        AddressPl address = clientPl.getAddress();

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals(new Date().toString(), clientPl.getBirthday().toString());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1L)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1L)
                        .name("Новый Год")
                        .build())
                .build()));

        //ToDo write test for orders

    }

    @Test
    public void test_when_call_deleteClientById_then_return_result() {

        doNothing().when(clientService).deleteClientById(any());

        clientController.deleteClientById(1L);

        verify(clientService, times(1)).deleteClientById(any());

    }

    @Test
    public void test_when_call_searchByParams_then_return_result() {

        List<ClientPl> clientPlList = new ArrayList<>();
        Set<DiscountPl> discountPlList = new HashSet<>();
        List<OrderPl> orderPlList = new ArrayList<>();

        discountPlList.add(DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(1L)
                .name("Новый Год")
                .build()));
        clientPlList.add(ClientPlStub.getStub(
                AddressPlStub.getStub(),
                orderPlList,
                discountPlList
        ));

        when(clientService.searchByParams(any())).thenReturn(clientPlList);
        List<ClientPl> clients = clientController.searchByParams(
                1L,
                "",
                "",
                "",
                "",
                "",
                ""
        );

        ClientPl clientPl = clients.get(0);
        AddressPl address = clientPl.getAddress();

        assertEquals(1, Integer.parseInt(clientPl.getId().toString()));
        assertEquals("Ivan", clientPl.getFirstName());
        assertEquals("Ivanovich", clientPl.getSecondName());
        assertEquals("Ivanov", clientPl.getLastName());
        assertEquals("email", clientPl.getEmail());
        assertEquals("13145", clientPl.getTelephone());
        assertEquals(new Date().toString(), clientPl.getBirthday().toString());

        assertEquals(1, Integer.parseInt(address.getId().toString()));
        assertEquals("Беларусь", address.getCountry());
        assertEquals("Гродненская", address.getRegion());
        assertEquals("Гродно", address.getLocality());
        assertEquals(Long.valueOf(20).toString(), address.getRoom().toString());
        assertEquals("Ленина 10", address.getStreet());
        assertEquals(Long.valueOf(230041).toString(), address.getIndex().toString());

        assertTrue(clientPl.getDiscounts().contains(DiscountPl.builder()
                .id(1L)
                .value(20)
                .type(DiscountTypePl.builder()
                        .id(1L)
                        .name("Новый Год")
                        .build())
                .build()));

        //ToDo write test for orders

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

}
