package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.OrderMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderPl addOrder(OrderPl orderPl) {
        Order order = orderRepository.save(orderMapper.mapperToOrder(orderPl));
        return orderMapper.mapperToOrderPl(order);
    }

    @Override
    public List<OrderPl> getOrders() {
        return null;
    }

    @Override
    public OrderPl editOrders(OrderPl orderPl) {
        return null;
    }

    @Override
    public OrderPl getOrderById(Long id) {
        return null;
    }

    @Override
    public void deleteOrderById(Long id) {

    }

}
