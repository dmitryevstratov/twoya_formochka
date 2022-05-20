package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientWithDiscountPl;

import java.util.List;

public interface ClientController {

    ClientPl addClient(ClientPl clientPl);

    List<ClientPl> getClients();

    List<ClientPl> getClientsWithDiscounts();

    ClientPl editClient(ClientPl clientPl);

    ClientPl editClientWithDiscount(ClientWithDiscountPl clientPl);

    ClientPl getClientById(Integer id);

    void deleteClientById(Integer id);

    List<ClientPl> searchByParams(Integer id,
                                  String firstName,
                                  String lastName,
                                  String secondName,
                                  String birthday,
                                  String email,
                                  String telephone);

    List<ClientPl> searchByParams(Integer id,
                                  String firstName,
                                  String lastName,
                                  String discountName);

}
