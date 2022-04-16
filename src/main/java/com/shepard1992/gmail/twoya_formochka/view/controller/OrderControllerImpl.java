package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.OrderController;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class OrderControllerImpl implements ModelAndViewController, OrderController {

    private static final List<OrderPl> orderList = List.of();
    private static final List<ClientPl> clientList = List.of(
            ClientPl.builder().id(1L).build(),
            ClientPl.builder().id(2L).build(),
            ClientPl.builder().id(3L).build(),
            ClientPl.builder().id(4L).build()
    );

    @Override
    @GetMapping("/orders.html")
    public ModelAndView getView(Model model) {
        return new ModelAndView("orders");
    }

    @Override
    @PostMapping("/orders/create")
    public OrderPl addOrder(OrderPl orderPl) {
        return null;
    }

    @Override
    @GetMapping("/orders")
    public List<OrderPl> getOrders() {
        return orderList;
    }

    @Override
    public OrderPl editOrders(OrderPl orderPl) {
        return null;
    }

    @Override
    public OrderPl getOrderById(Long id) {
        return null;
    }

    @Override
    public void deleteOrderById(Long id) {

    }

    @Override
    @GetMapping("/orders/search")
    public List<ClientPl> searchByParams(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "telephone", required = false) String telephone
    ) {
        return clientList;
    }

}
