package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;

import java.util.List;

public interface OrderController {

    OrderPl addOrder(OrderPl orderPl);

    List<OrderPl> getOrders();

    OrderPl editOrders(OrderPl orderPl);

    OrderPl getOrderById(Long id);

    void deleteOrderById(Long id);

    List<ClientPl> searchByParams(Long id,
                                  String firstName,
                                  String lastName,
                                  String telephone);

}
