package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.OrderController;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    public CreateOrderPl addOrder(@RequestBody CreateOrderPl createOrderPl) {
        return service.addOrder(createOrderPl);
    }

    @Override
    @GetMapping("/orders")
    public List<GetOrderPl> getOrders() {
        return service.getOrders();
    }

    @GetMapping("orders/search")
    public List<GetOrderPl> searchByParams(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "dateCreate", required = false) String dateCreate,
            @RequestParam(value = "dateClosed", required = false) String dateClosed,
            @RequestParam(value = "selectedStatus", required = false) String selectedStatus,
            @RequestParam(value = "priceMin", required = false) String priceMin,
            @RequestParam(value = "priceMax", required = false) String priceMax,
            @RequestParam(value = "count", required = false) String count
    ) {
        return service.searchByParams(FilterOrderPl.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .dateCreate(dateCreate)
                .dateClosed(dateClosed)
                .selectedStatus(selectedStatus)
                .priceMax(priceMax)
                .priceMin(priceMin)
                .count(count)
                .build());
    }

    @Override
    public CreateOrderPl editOrders(CreateOrderPl createOrderPl) {
        return service.editOrders(createOrderPl);
    }

    @Override
    public CreateOrderPl getOrderById(Long id) {
        return service.getOrderById(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        service.deleteOrderById(id);
    }

}
