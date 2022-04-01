package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;

import java.util.Date;
import java.util.List;

public interface ClientController {

    ClientPl addClient(ClientPl clientPl);

    List<ClientPl> getClients();

    ClientPl editClient(ClientPl clientPl);

    ClientPl getClientById(Long id);

    void deleteClientById(Long id);

    List<ClientPl> searchByParams(Long id,
                                  String firstName,
                                  String lastName,
                                  String secondName,
                                  String birthday,
                                  String email,
                                  String telephone);

}
