package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ClientController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterClientPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ClientControllerImpl implements ModelAndViewController, ClientController {

    private final ClientService service;

    @Autowired
    public ClientControllerImpl(ClientService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/clients.html")
    public ModelAndView getView(Model model) {
        return new ModelAndView("clients");
    }

    @Override
    @GetMapping("/clients")
    public List<ClientPl> getClients() {
        return service.getClients();
    }

    @Override
    @GetMapping("/clients/{id}")
    public ClientPl getClientById(@PathVariable Integer id) {
        return service.getClientById(id);
    }

    @Override
    @PostMapping("/clients/create")
    public ClientPl addClient(@RequestBody ClientPl clientPl) {
        return service.addClient(clientPl);
    }

    @Override
    @PutMapping("/clients/edit")
    public ClientPl editClient(@RequestBody ClientPl clientPl) {
        return service.editClient(clientPl);
    }

    @Override
    @DeleteMapping("/clients/{id}")
    public void deleteClientById(@PathVariable Integer id) {
        service.deleteClientById(id);
    }

    @Override
    @GetMapping("/clients/search")
    public List<ClientPl> searchByParams(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "birthday", required = false) String birthday,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "telephone", required = false) String telephone
    ) {
        return service.searchByParams(FilterClientPl.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .secondName(secondName)
                .birthday(birthday)
                .email(email)
                .telephone(telephone)
                .build());
    }

}
