package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import com.shepard1992.gmail.twoya_formochka.view.model.*;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.shepard1992.gmail.twoya_formochka.service.utils.DateUtil.converterDate;

@Component
public class OrderMapper {

    public Order mapperToOrder(CreateOrderPl createOrderPl) {
        List<Item> items = new ArrayList<>();

        createOrderPl.getItems().forEach(itemsOrderPl -> {
            if (itemsOrderPl != null) {
                for (int i = 0; i < itemsOrderPl.getCount(); i++) {
                    items.add(Item.builder()
                            .id(itemsOrderPl.getId())
                            .build());
                }
            }
        });

        Order order = Order.builder()
                .id(createOrderPl.getIdOrder())
                .dateCreate(converterDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
                .client(Client.builder()
                        .id(createOrderPl.getIdClient())
                        .build())
                .status(StatusOrder.CREATED)
                .items(items)
                .totalPrice(createOrderPl.getPrice())
                .count(items.size())
                .build();

        if (createOrderPl.getIdDiscount() != null) {
            order.setDiscount(Discount.builder().id(createOrderPl.getIdDiscount()).build());
        }

        return order;
    }

    public CreateOrderPl mapperToOrderPl(Order order) {
        Optional<Discount> discount = Optional.ofNullable(order.getDiscount());
        List<ItemsOrderPl> itemsOrderList = new ArrayList<>();
        Map<Integer, Integer> itemsOrdersMap = new HashMap<>();

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

        CreateOrderPl createOrderPl = CreateOrderPl.builder()
                .idClient(order.getClient().getId())
                .price(order.getTotalPrice())
                .items(itemsOrderList)
                .build();

        discount.ifPresent(value -> createOrderPl.setIdDiscount(value.getId()));

        return createOrderPl;
    }

    public GetOrderPl mapperToGetOrderPl(Order order) {
        DiscountPl discountPl = null;
        String dateClosed = (order.getDateClosed() != null) ? order.getDateClosed().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "";

        if (order.getDiscount() != null) {
            discountPl = DiscountPl.builder()
                    .id(order.getDiscount().getId())
                    .type(DiscountTypePl.builder()
                            .id(order.getDiscount().getType().getId())
                            .name(order.getDiscount().getType().getName())
                            .build())
                    .value(order.getDiscount().getValue())
                    .build();
        }

        return GetOrderPl.builder()
                .id(order.getId())
                .client(ClientPl.builder()
                        .id(order.getClient().getId())
                        .firstName(order.getClient().getFirstName())
                        .lastName(order.getClient().getLastName())
                        .build())
                .dateCreate(order.getDateCreate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .dateClosed(dateClosed)
                .discount(discountPl)
                .totalPrice(order.getTotalPrice())
                .countItems(order.getItems().size())
                .status(order.getStatus())
                .build();
    }

    public GetOrderToUpdatePl mapperToUpdateOrderPl(Order order) {
        Client client = order.getClient();
        Discount discount = order.getDiscount();

        List<ItemPl> itemPlList = order.getItems().stream().map(item -> ItemPl.builder()
                .id(item.getId())
                .type(ItemTypePl.builder()
                        .id(item.getType().getId())
                        .name(item.getType().getName())
                        .build())
                .name(item.getName())
                .price(item.getPrice())
                .category(ItemCategoryPl.builder()
                        .id(item.getCategory().getId())
                        .name(item.getCategory().getName())
                        .build())
                .size(item.getSize())
                .build()).collect(Collectors.toList());

        GetOrderToUpdatePl orderPl = GetOrderToUpdatePl.builder()
                .clientPl(ClientPl.builder()
                        .id(client.getId())
                        .firstName(client.getFirstName())
                        .lastName(client.getLastName())
                        .build())
                .itemPlList(itemPlList)
                .build();

        if (discount != null) {
            orderPl.setDiscountPl(DiscountPl.builder()
                    .id(discount.getId())
                    .type(DiscountTypePl.builder()
                            .id(discount.getType().getId())
                            .name(discount.getType().getName())
                            .build())
                    .value(discount.getValue())
                    .build());
        }

        return orderPl;
    }
}
