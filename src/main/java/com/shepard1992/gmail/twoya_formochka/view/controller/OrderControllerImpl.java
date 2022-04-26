package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.OrderController;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class OrderControllerImpl implements ModelAndViewController, OrderController {

    private final OrderService service;

    @Autowired
    public OrderControllerImpl(OrderService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/orders.html")
    public ModelAndView getView(Model model) {
        return new ModelAndView("orders");
    }

    @Override
    @PostMapping("/orders/create")
    public OrderPl addOrder(@RequestBody OrderPl orderPl) {
        return service.addOrder(orderPl);
    }

    @Override
    @GetMapping("/orders")
    public List<OrderPl> getOrders() {
        return service.getOrders();
    }

    @Override
    public OrderPl editOrders(OrderPl orderPl) {
        return service.editOrders(orderPl);
    }

    @Override
    public OrderPl getOrderById(Long id) {
        return service.getOrderById(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        service.deleteOrderById(id);
    }

}
