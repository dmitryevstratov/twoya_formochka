package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderToUpdatePl;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface OrderController {

    CreateOrderPl addOrder(CreateOrderPl createOrderPl);

    List<GetOrderPl> getOrders();

    List<GetOrderPl> getOrdersStatus();

    List<GetOrderPl> searchByParams(
            Integer id,
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

    CreateOrderPl editOrderStatus(Integer id, String status);

    GetOrderToUpdatePl getOrderById(Integer id);

    void deleteOrderById(Integer id);

    ModelAndView getViewOrdersStatus(Model model);

}
