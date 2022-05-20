package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.specification.ClientSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ClientMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientWithDiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterClientPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;
    private final FilterMapper filterMapper;
    private final DiscountRepository discountRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, ClientMapper mapper, FilterMapper filterMapper, DiscountRepository discountRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.filterMapper = filterMapper;
        this.discountRepository = discountRepository;
    }

    @Override
    public ClientPl addClient(ClientPl clientPl) {
        Client client = repository.save(mapper.mapperToClient(clientPl));
        return mapper.mapperToClientPl(client);
    }

    @Override
    public List<ClientPl> getClients() {
        return repository
                .findAll()
                .stream()
                .map(mapper::mapperToClientPl)
                .sorted((c, c1) -> Integer.parseInt((c.getId() - c1.getId()) + ""))
                .collect(Collectors.toList());
    }

    @Override
    public ClientPl editClient(ClientPl clientPl) {
        Client client = repository.save(mapper.mapperToClient(clientPl));
        return mapper.mapperToClientPl(client);
    }

    @Override
    public ClientPl getClientById(Integer id) {
        return mapper.mapperToClientPl(Objects.requireNonNull(repository.findById(id).orElse(null)));
    }

    @Override
    public void deleteClientById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ClientPl> searchByParams(FilterClientPl filterClientPl) {
        return repository.findAll(new ClientSpecification(filterMapper.mapperToFilter(filterClientPl))).stream()
                .map(mapper::mapperToClientPl)
                .sorted((c, c1) -> Integer.parseInt((c.getId() - c1.getId()) + ""))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientPl> clientWithDiscountSearchByParams(FilterClientPl filterClientPl) {
        return repository.findAll(new ClientSpecification(filterMapper.mapperToFilter(filterClientPl))).stream()
                .map(mapper::mapperToClientPl)
                .sorted((c, c1) -> Integer.parseInt((c.getId() - c1.getId()) + ""))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientPl> getClientsWithDiscounts() {
        return repository.findAll().stream()
                .map(mapper::mapperToClientPl)
                .sorted((c, c1) -> Integer.parseInt((c.getId() - c1.getId()) + ""))
                .collect(Collectors.toList());
    }

    @Override
    public ClientPl editClientWithDiscount(ClientWithDiscountPl clientPl) {
        List<Discount> discountList = new ArrayList<>();
        Client client = repository.findById(clientPl.getId()).get();
        if (!clientPl.getDiscounts().isEmpty()) {
            clientPl.getDiscounts().forEach(discountPl -> {
                Integer value = discountPl.getValue();
                String typeName = discountPl.getType().getName();
                Discount discount = discountRepository.findByTypeNameAndValue(typeName, value);
                discountList.add(discount);
            });
        }
        client.setDiscounts(discountList);

        return mapper.mapperToClientPlWithDiscount(client);
    }

}
