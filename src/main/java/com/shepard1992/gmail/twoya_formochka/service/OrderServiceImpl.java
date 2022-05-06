package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.specification.OrderSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.OrderMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.CreateOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderPl;
import com.shepard1992.gmail.twoya_formochka.view.model.GetOrderToUpdatePl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final FilterMapper filterMapper;
    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, FilterMapper filterMapper, OrderRepository repository) {
        this.orderMapper = orderMapper;
        this.filterMapper = filterMapper;
        this.repository = repository;
    }

    @Override
    public CreateOrderPl addOrder(CreateOrderPl createOrderPl) {
        System.out.println(createOrderPl);
        Order order = repository.save(orderMapper.mapperToOrder(createOrderPl));
        return orderMapper.mapperToOrderPl(order);
    }

    @Override
    public List<GetOrderPl> getOrders() {
        return repository.findAll().stream()
                .map(orderMapper::mapperToGetOrderPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetOrderPl> searchByParams(FilterOrderPl filterOrderPl) {
        return repository.findAll(new OrderSpecification(filterMapper.mapperToOrderFilter(filterOrderPl))).stream()
                .map(orderMapper::mapperToGetOrderPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public CreateOrderPl editOrders(CreateOrderPl createOrderPl) {
        Order order = repository.save(orderMapper.mapperToOrder(createOrderPl));
        return orderMapper.mapperToOrderPl(order);
    }

    @Override
    public GetOrderToUpdatePl getOrderById(Integer id) {
        return orderMapper.mapperToUpdateOrderPl(repository.findById(id).get());
    }

    @Override
    public void deleteOrderById(Integer id) {

    }

}
