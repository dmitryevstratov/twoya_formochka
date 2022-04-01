package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterPl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public ClientPl addClient(ClientPl clientPl) {
        //ToDo обращаться в репозиторий
        System.out.println("addClient");
        return null;
    }

    @Override
    public List<ClientPl> getClients() {
        System.out.println("getClients");
        //ToDo обращаться в репозиторий
        return null;
    }

    @Override
    public ClientPl editClient(ClientPl clientPl) {
        System.out.println("editClient");
        //ToDo обращаться в репозиторий
        return null;
    }

    @Override
    public ClientPl getClientById(Long id) {
        System.out.println("getClientById");
        //ToDo обращаться в репозиторий
        return null;
    }

    @Override
    public void deleteClientById(Long id) {
        System.out.println("deleteClientById");
        //ToDo обращаться в репозиторий
    }

    @Override
    public List<ClientPl> searchByParams(FilterPl filterPl) {
        System.out.println("searchByParams");
        //ToDo обращаться в репозиторий
        return null;
    }

}
