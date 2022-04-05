package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.repository.model.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.model.Order;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ClientMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import view.stubs.AddressPlStub;
import view.stubs.AddressStub;
import view.stubs.ClientPlStub;
import view.stubs.ClientStub;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class ClientMapperTest {

    @Autowired
    private ClientMapper clientMapper;

    @Test
    public void test_mapperToClient(){
        List<OrderPl> orderPlList = new ArrayList<>();
        Set<DiscountPl> discountPlSet = new HashSet<>();
        ClientPl stub = ClientPlStub.getStub(
                AddressPlStub.getStub(),
                orderPlList,
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
    public void test_mapperToClientPl(){
        List<Order> orderList = new ArrayList<>();
        Set<Discount> discountSet = new HashSet<>();
        Client stub = ClientStub.getStub(
                AddressStub.getStub(),
                orderList,
                discountSet
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
