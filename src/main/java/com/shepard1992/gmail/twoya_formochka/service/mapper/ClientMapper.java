package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    private final AddressMapper addressMapper;

    @Autowired
    public ClientMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Client mapperToClient(ClientPl clientPl) {
        AddressPl address = clientPl.getAddress();
        return Client.builder()
                .id(clientPl.getId())
                .firstName(clientPl.getFirstName())
                .lastName(clientPl.getLastName())
                .secondName(clientPl.getSecondName())
                .birthday(clientPl.getBirthday())
                .email(clientPl.getEmail())
                .telephone(clientPl.getTelephone())
                .address(addressMapper.mapperToAddress(address))
                .build();
    }

    public ClientPl mapperToClientPl(Client client) {
        return ClientPl.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .secondName(client.getSecondName())
                .birthday(client.getBirthday())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .address(addressMapper.mapperToAddressPl(client.getAddress()))
                .build();
    }

}
