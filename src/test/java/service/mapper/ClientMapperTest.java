package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ClientMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import stubs.AddressPlStub;
import stubs.AddressStub;
import stubs.ClientPlStub;
import stubs.ClientStub;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class ClientMapperTest {

    @Autowired
    private ClientMapper clientMapper;

    @Test
    public void test_mapperToClient() {
        List<CreateOrderPl> createOrderPlList = new ArrayList<>();
        List<DiscountPl> discountPlSet = new ArrayList<>();
        ClientPl stub = ClientPlStub.getStub(
                AddressPlStub.getStub(),
                createOrderPlList,
                discountPlSet
        );

        Client client = clientMapper.mapperToClient(stub);

        assertEquals(stub.getId(), client.getId());
        assertEquals(stub.getFirstName(), client.getFirstName());
        assertEquals(stub.getLastName(), client.getLastName());
        assertEquals(stub.getSecondName(), client.getSecondName());
        assertEquals(stub.getBirthday(), client.getBirthday());
        assertEquals(stub.getEmail(), client.getEmail());
        assertEquals(stub.getTelephone(), client.getTelephone());
    }

    @Test
    public void test_mapperToClientPl() {
        List<Order> orderList = new ArrayList<>();
        List<Discount> discountList = new ArrayList<>();
        Client stub = ClientStub.getStub(
                AddressStub.getStub(),
                orderList,
                discountList
        );

        ClientPl clientPl = clientMapper.mapperToClientPl(stub);

        assertEquals(stub.getId(), clientPl.getId());
        assertEquals(stub.getFirstName(), clientPl.getFirstName());
        assertEquals(stub.getLastName(), clientPl.getLastName());
        assertEquals(stub.getSecondName(), clientPl.getSecondName());
        assertEquals(stub.getBirthday(), clientPl.getBirthday());
        assertEquals(stub.getEmail(), clientPl.getEmail());
        assertEquals(stub.getTelephone(), clientPl.getTelephone());
    }

}
