package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemsOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.*;

@Component
public class OrderMapper {

    public Order mapperToOrder(OrderPl orderPl) {
        List<Item> items = new ArrayList<>();
        orderPl.getItems().forEach(itemsOrderPl -> {
            if (itemsOrderPl != null) {
                for (int i = 0; i < itemsOrderPl.getCount(); i++) {
                    items.add(Item.builder()
                            .id(itemsOrderPl.getId())
                            .build());
                }
            }
        });

        Order order = Order.builder()
                .dateCreate(ZonedDateTime.now())
                .client(Client.builder()
                        .id(orderPl.getIdClient())
                        .build())
                .status(StatusOrder.CREATED)
                .items(items)
                .totalPrice(orderPl.getPrice())
                .build();

        if (orderPl.getIdDiscount() != null) {
            order.setDiscount(Discount.builder().id(orderPl.getIdDiscount()).build());
        }

        return order;
    }

    public OrderPl mapperToOrderPl(Order order) {
        Optional<Discount> discount = Optional.ofNullable(order.getDiscount());
        List<ItemsOrderPl> itemsOrderList = new ArrayList<>();
        Map<Long, Integer> itemsOrdersMap = new HashMap<>();

        order.getItems().forEach(item -> {
            Integer count = itemsOrdersMap.get(item.getId());
            if (count != null) {
                itemsOrdersMap.put(item.getId(), ++count);
            } else {
                itemsOrdersMap.put(item.getId(), 1);
            }
        });

        itemsOrdersMap.forEach((key, value) -> itemsOrderList.add(ItemsOrderPl.builder()
                .id(key)
                .count(value)
                .build()));

        OrderPl orderPl = OrderPl.builder()
                .idClient(order.getClient().getId())
                .price(order.getTotalPrice())
                .items(itemsOrderList)
                .build();

        discount.ifPresent(value -> orderPl.setIdDiscount(value.getId()));

        return orderPl;
    }
}
