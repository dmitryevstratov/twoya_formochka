package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;

import java.util.List;

public interface OrderController {

    CreateOrderPl addOrder(CreateOrderPl createOrderPl);

    List<GetOrderPl> getOrders();

    List<GetOrderPl> searchByParams(
            Long id,
            String firstName,
            String lastName,
            String dateCreate,
            String dateClosed,
            String selectedStatus,
            String priceMin,
            String priceMax,
            String count
    );

    CreateOrderPl editOrders(CreateOrderPl createOrderPl);

    CreateOrderPl getOrderById(Long id);

    void deleteOrderById(Long id);

}
