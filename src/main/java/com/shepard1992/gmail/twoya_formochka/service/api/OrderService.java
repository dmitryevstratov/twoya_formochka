package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderToUpdatePl;

import java.util.List;

public interface OrderService {

    CreateOrderPl addOrder(CreateOrderPl createOrderPl);

    List<GetOrderPl> getOrders();

    List<GetOrderPl> getOrdersStatus();

    List<GetOrderPl> searchByParams(FilterOrderPl filterOrderPl);

    CreateOrderPl editOrders(CreateOrderPl createOrderPl);

    GetOrderToUpdatePl getOrderById(Integer id);

    void deleteOrderById(Integer id);

    CreateOrderPl editOrderStatus(Integer id, String status);

}
