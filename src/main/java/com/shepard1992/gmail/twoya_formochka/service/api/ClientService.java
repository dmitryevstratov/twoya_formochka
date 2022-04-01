package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterPl;

import java.util.List;

public interface ClientService {

    ClientPl addClient(ClientPl clientPl);

    List<ClientPl> getClients();

    ClientPl editClient(ClientPl clientPl);

    ClientPl getClientById(Long id);

    void deleteClientById(Long id);

    List<ClientPl> searchByParams(FilterPl filterPl);

}