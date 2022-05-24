package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.view.model.*;

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

    List<GetMonthStatisticPl> searchByParams(String dateStart, String dateEnd);
}
