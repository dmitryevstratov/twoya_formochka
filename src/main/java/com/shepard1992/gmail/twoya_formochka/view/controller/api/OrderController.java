package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;

import java.util.List;

public interface OrderController {

    OrderPl addOrder(OrderPl orderPl);

    List<OrderPl> getOrders();

    OrderPl editOrders(OrderPl orderPl);

    OrderPl getOrderById(Long id);

    void deleteOrderById(Long id);

}
