package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.view.controller.api.ClientController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
public class ClientControllerImpl implements ModelAndViewController, ClientController {

    //ToDo убрать, когда будет реализован слой сервиса и репо
    private static final List<ClientPl> clientPlList = new ArrayList<>();

    static {
        clientPlList.add(ClientPl.builder()
                .id(1L)
                .firstName("Vadim")
                .lastName("Ivanov")
                .secondName("Petrovich")
                .address(AddressPl.builder().build())
                .birthday(new Date())
                .discounts(new HashSet<>())
                .email("")
                .orders(new ArrayList<>())
                .build()
        );
    }

    @Override
    @GetMapping("/clients.html")
    public ModelAndView getView(Model model) {
        //ToDo вместо clientPlList должно быть обращению к сервису
        model.addAttribute("clientsPl", clientPlList);
        return new ModelAndView("clients");
    }

    @Override
    @PostMapping("/createClient")
    public ClientPl addClient(@RequestBody ClientPl clientPl) {
        ///ToDo вместо clientPlList должно быть обращению к сервису
        clientPlList.add(clientPl);
        System.out.println(clientPl);
        return clientPl;
    }
}
