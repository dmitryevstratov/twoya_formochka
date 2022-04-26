package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Address;
import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address mapperToAddress(AddressPl addressPl) {
        return Address.builder()
                .id(addressPl.getId())
                .country(addressPl.getCountry())
                .region(addressPl.getRegion())
                .locality(addressPl.getLocality())
                .street(addressPl.getStreet())
                .room(addressPl.getRoom())
                .index(addressPl.getIndex())
                .additionalInfo(addressPl.getAdditionalInfo())
                .build();
    }

    public AddressPl mapperToAddressPl(Address address) {
        return AddressPl.builder()
                .id(address.getId())
                .country(address.getCountry())
                .region(address.getRegion())
                .locality(address.getLocality())
                .street(address.getStreet())
                .room(address.getRoom())
                .index(address.getIndex())
                .additionalInfo(address.getAdditionalInfo())
                .build();
    }

}
