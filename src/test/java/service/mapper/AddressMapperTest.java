package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Address;
import com.shepard1992.gmail.twoya_formochka.service.mapper.AddressMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import stubs.AddressPlStub;
import stubs.AddressStub;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void test_mapperToAddress() {
        Address address = addressMapper.mapperToAddress(AddressPlStub.getStub());

        assertEquals(AddressPlStub.getStub().getId(), address.getId());
        assertEquals(AddressPlStub.getStub().getCountry(), address.getCountry());
        assertEquals(AddressPlStub.getStub().getRegion(), address.getRegion());
        assertEquals(AddressPlStub.getStub().getLocality(), address.getLocality());
        assertEquals(AddressPlStub.getStub().getStreet(), address.getStreet());
        assertEquals(AddressPlStub.getStub().getRoom(), address.getRoom());
        assertEquals(AddressPlStub.getStub().getIndex(), address.getIndex());
        assertEquals(AddressPlStub.getStub().getAdditionalInfo(), address.getAdditionalInfo());
    }

    @Test
    public void test_mapperToAddressPl() {
        AddressPl addressPl = addressMapper.mapperToAddressPl(AddressStub.getStub());

        assertEquals(AddressPlStub.getStub().getId(), addressPl.getId());
        assertEquals(AddressPlStub.getStub().getCountry(), addressPl.getCountry());
        assertEquals(AddressPlStub.getStub().getRegion(), addressPl.getRegion());
        assertEquals(AddressPlStub.getStub().getLocality(), addressPl.getLocality());
        assertEquals(AddressPlStub.getStub().getStreet(), addressPl.getStreet());
        assertEquals(AddressPlStub.getStub().getRoom(), addressPl.getRoom());
        assertEquals(AddressPlStub.getStub().getIndex(), addressPl.getIndex());
        assertEquals(AddressPlStub.getStub().getAdditionalInfo(), addressPl.getAdditionalInfo());
    }

}
