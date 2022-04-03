package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.service.mapping.ClientMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    @Override
    public ClientPl addClient(ClientPl clientPl) {
        Client client = clientRepository.save(mapper.mapperToClient(clientPl));
        return mapper.mapperToClient(client);
    }

    @Override
    public List<ClientPl> getClients() {
        return clientRepository
                .findAll()
                .stream()
                .map(mapper::mapperToClient)
                .collect(Collectors.toList());
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
