package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Address;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    private final AddressMapper addressMapper;
    private final DiscountMapper discountMapper;

    @Autowired
    public ClientMapper(AddressMapper addressMapper, DiscountMapper discountMapper) {
        this.addressMapper = addressMapper;
        this.discountMapper = discountMapper;
    }

    public Client mapperToClient(ClientPl clientPl) {
        Address address = addressMapper.mapperToAddress(clientPl.getAddress());
        List<Discount> discounts = (clientPl.getDiscounts() == null) ? null : clientPl.getDiscounts().stream()
                .map(discountMapper::mapperToDiscount)
                .collect(Collectors.toList());

        return Client.builder()
                .id(clientPl.getId())
                .firstName(clientPl.getFirstName())
                .lastName(clientPl.getLastName())
                .secondName(clientPl.getSecondName())
                .birthday(clientPl.getBirthday())
                .email(clientPl.getEmail())
                .telephone(clientPl.getTelephone())
                .address(address)
                .discounts(discounts)
                .build();
    }

    public ClientPl mapperToClientPl(Client client) {
        AddressPl addressPl = addressMapper.mapperToAddressPl(client.getAddress());
        List<DiscountPl> discountsPl = (client.getDiscounts() == null) ? null : client.getDiscounts().stream()
                .map(discountMapper::mapperToDiscountPl)
                .collect(Collectors.toList());

        return ClientPl.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .secondName(client.getSecondName())
                .birthday(client.getBirthday())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .address(addressPl)
                .discounts(discountsPl)
                .build();
    }

    public ClientPl mapperToClientPlWithDiscount(Client client) {
        List<DiscountPl> discountsPl = (client.getDiscounts() == null) ? null : client.getDiscounts().stream()
                .map(discountMapper::mapperToDiscountPl)
                .collect(Collectors.toList());

        return ClientPl.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .secondName(client.getSecondName())
                .discounts(discountsPl)
                .build();
    }

}
