package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.view.controller.api.ClientController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ClientControllerImpl implements ModelAndViewController, ClientController {

    private static List<ClientPl> clientPlList = new ArrayList<>();

    @Override
    @GetMapping("/clients.html")
    public ModelAndView getView(Model model) {
        return new ModelAndView("clients");
    }

    @Override
    @GetMapping("/clients")
    public List<ClientPl> getClients() {
        ///ToDo вместо clientPlList должно быть обращению к сервису
        clientPlList.add(ClientPl.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Baturov")
                .secondName("Ivanovich")
                .birthday(new Date())
                .email("www.iiqw.ru")
                .telephone("213124")
                .address(AddressPl.builder()
                        .id(1L)
                        .country("Беларусь")
                        .region("Гродненская")
                        .locality("Гродно")
                        .street("Ле")
                        .room(8)
                        .index(20)
                        .build())
                .build());
        clientPlList.add(ClientPl.builder()
                .id(2L)
                .firstName("Grigory")
                .lastName("Shedulov")
                .secondName("Sidorovich")
                .birthday(new Date())
                .email("www.w222dff1.ru")
                .telephone("9007772")
                .address(AddressPl.builder()
                        .id(1L)
                        .country("Россия")
                        .region("Московская")
                        .locality("Москва")
                        .street("Ле")
                        .room(18)
                        .index(2220)
                        .build())
                .build());
        return clientPlList;
    }

    @Override
    @PutMapping("/clients/edit")
    public ClientPl editClient(@RequestBody ClientPl clientPl) {
        //ToDo вместо clientPlList должно быть обращению к сервису
        ClientPl updateClient = clientPlList.get(Integer.parseInt(clientPl.getId().toString()) - 1);
        updateClient.setFirstName(clientPl.getFirstName());
        updateClient.setLastName(clientPl.getLastName());
        updateClient.setSecondName(clientPl.getSecondName());
        System.out.println(updateClient);
        return updateClient;
    }

    @Override
    @GetMapping("/clients/{id}")
    public ClientPl getClientById(@PathVariable Long id) {
        //ToDo вместо clientPlList должно быть обращению к сервису
        return clientPlList.get(Integer.parseInt(id.toString()) - 1);
    }

    @Override
    @DeleteMapping("/clients/{id}")
    public void deleteClientById(@PathVariable Long id) {
        //ToDo вместо clientPlList должно быть обращению к сервису
        System.out.println("Было " + clientPlList.size());
        clientPlList.remove(Integer.parseInt(id.toString()) -1);
        System.out.println("Стало " + clientPlList.size());
    }

    @Override
    @PostMapping("/clients/create")
    public ClientPl addClient(@RequestBody ClientPl clientPl) {
        ///ToDo вместо clientPlList должно быть обращению к сервису
        System.out.println(clientPl);
        clientPlList.add(clientPl);
        return clientPl;
    }
}
