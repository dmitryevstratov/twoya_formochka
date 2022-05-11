package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.OrderController;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderToUpdatePl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/orders-status.html")
    public ModelAndView getViewOrdersStatus(Model model) {
        return new ModelAndView("orders-status");
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

    @Override
    @GetMapping("/orders-status")
    public List<GetOrderPl> getOrdersStatus() {
        return service.getOrdersStatus();
    }

    @GetMapping("orders/search")
    public List<GetOrderPl> searchByParams(
            @RequestParam(value = "id", required = false) Integer id,
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
    @PutMapping("/orders/edit")
    public CreateOrderPl editOrders(@RequestBody CreateOrderPl createOrderPl) {
        return service.editOrders(createOrderPl);
    }

    @Override
    @PutMapping("/orders-status/edit")
    public CreateOrderPl editOrderStatus(@RequestParam Integer id, @RequestParam String status) {
        return service.editOrderStatus(id, status);
    }

    @Override
    @GetMapping("/orders/{id}")
    public GetOrderToUpdatePl getOrderById(@PathVariable Integer id) {
        return service.getOrderById(id);
    }

    @Override
    @DeleteMapping("/orders/{id}")
    public void deleteOrderById(@PathVariable Integer id) {
        service.deleteOrderById(id);
    }

}
