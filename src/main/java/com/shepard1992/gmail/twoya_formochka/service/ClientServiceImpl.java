package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.specification.ClientSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ClientMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;
    private final FilterMapper filterMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper mapper, FilterMapper filterMapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.filterMapper = filterMapper;
    }

    @Override
    public ClientPl addClient(ClientPl clientPl) {
        Client client = clientRepository.save(mapper.mapperToClient(clientPl));
        return mapper.mapperToClientPl(client);
    }

    @Override
    public List<ClientPl> getClients() {
        return clientRepository
                .findAll()
                .stream()
                .map(mapper::mapperToClientPl)
                .sorted((c, c1) -> Integer.parseInt((c.getId() - c1.getId()) + ""))
                .collect(Collectors.toList());
    }

    @Override
    public ClientPl editClient(ClientPl clientPl) {
        Client client = clientRepository.save(mapper.mapperToClient(clientPl));
        return mapper.mapperToClientPl(client);
    }

    @Override
    public ClientPl getClientById(Long id) {
        return mapper.mapperToClientPl(Objects.requireNonNull(clientRepository.findById(id).orElse(null)));
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientPl> searchByParams(FilterPl filterPl) {
        return clientRepository.findAll(new ClientSpecification(filterMapper.mapperToFilter(filterPl))).stream()
                .map(mapper::mapperToClientPl)
                .sorted((c, c1) -> Integer.parseInt((c.getId() - c1.getId()) + ""))
                .collect(Collectors.toList());
    }

}
