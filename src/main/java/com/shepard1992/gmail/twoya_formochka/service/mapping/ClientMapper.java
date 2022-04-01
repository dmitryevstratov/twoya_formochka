package com.shepard1992.gmail.twoya_formochka.service.mapping;

import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client mapperToClient(ClientPl clientPl) {
        return Client.builder()
                .id(clientPl.getId())
                .firstName(clientPl.getFirstName())
                .lastName(clientPl.getLastName())
                .secondName(clientPl.getSecondName())
                .birthday(clientPl.getBirthday())
                .email(clientPl.getEmail())
                .telephone(clientPl.getTelephone())
                .build();
    }

    public ClientPl mapperToClient(Client client) {
        return ClientPl.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .secondName(client.getSecondName())
                .birthday(client.getBirthday())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .build();
    }

}
