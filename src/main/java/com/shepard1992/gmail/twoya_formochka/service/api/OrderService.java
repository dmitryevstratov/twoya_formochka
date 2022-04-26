package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;

import java.util.List;

public interface OrderService {

    OrderPl addOrder(OrderPl orderPl);

    List<OrderPl> getOrders();

    OrderPl editOrders(OrderPl orderPl);

    OrderPl getOrderById(Long id);

    void deleteOrderById(Long id);

}
