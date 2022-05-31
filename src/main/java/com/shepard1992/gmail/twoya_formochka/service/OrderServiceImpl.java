package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.OrderRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import com.shepard1992.gmail.twoya_formochka.repository.specification.OrderSpecification;
import com.shepard1992.gmail.twoya_formochka.repository.specification.OrderStatisticSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.OrderMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder.CANCELED;
import static com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder.PAID;
import static com.shepard1992.gmail.twoya_formochka.service.utils.DateUtil.converterDate;
import static com.shepard1992.gmail.twoya_formochka.service.utils.DateUtil.parseDate;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final FilterMapper filterMapper;
    private final OrderRepository repository;
    private final ClientRepository clientRepository;
    private static final Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, FilterMapper filterMapper, OrderRepository repository, ClientRepository clientRepository) {
        this.orderMapper = orderMapper;
        this.filterMapper = filterMapper;
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    @Override
    public CreateOrderPl addOrder(CreateOrderPl createOrderPl) {
        log.debug("Add order: " + createOrderPl);
        Order order = repository.save(orderMapper.mapperToOrder(createOrderPl));
        return orderMapper.mapperToOrderPl(order);
    }

    @Override
    public List<GetOrderPl> getOrders() {
        log.debug("Get all orders");
        return repository.findAll().stream()
                .map(orderMapper::mapperToGetOrderPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetOrderPl> getOrdersStatus() {
        log.debug("Get all orders without status=CANCELED");
        return repository.findAll().stream()
                .filter(order -> !order.getStatus().equals(CANCELED))
                .map(orderMapper::mapperToGetOrderPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetOrderPl> searchByParams(FilterOrderPl filterOrderPl) {
        log.debug("Get order by params: " + filterOrderPl);
        return repository.findAll(new OrderSpecification(filterMapper.mapperToOrderFilter(filterOrderPl))).stream()
                .map(orderMapper::mapperToGetOrderPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public CreateOrderPl editOrders(CreateOrderPl createOrderPl) {
        log.debug("Edit order: " + createOrderPl);
        Order order = repository.save(orderMapper.mapperToOrder(createOrderPl));
        return orderMapper.mapperToOrderPl(order);
    }

    @Override
    public GetOrderToUpdatePl getOrderById(Integer id) {
        log.debug("Get order by id= " + id);
        return orderMapper.mapperToUpdateOrderPl(repository.findById(id).get());
    }

    @Override
    public void deleteOrderById(Integer id) {
        log.debug("Delete order by id= " + id);
        repository.deleteById(id);
    }

    @Override
    public CreateOrderPl editOrderStatus(Integer id, String status) {
        log.debug("Edit order by params: id= " + id + ", status=" + status);
        Order order = repository.findById(id).get();
        StatusOrder statusOrder = StatusOrder.valueOf(status);

        if (statusOrder == CANCELED) {
            order.setDateClosed(converterDate(ZonedDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        }
        if (statusOrder == PAID) {
            Discount discount = order.getDiscount();
            if (discount != null) {
                List<Discount> newClientDiscounts = new ArrayList<>();
                Client client = clientRepository.findById(order.getClient().getId()).get();
                client.getDiscounts().forEach(disc -> {
                    if (!disc.getId().equals(discount.getId())) {
                        newClientDiscounts.add(disc);
                    }
                });
                client.setDiscounts(newClientDiscounts);
                clientRepository.save(client);
            }
        }
        order.setStatus(statusOrder);
        repository.save(order);

        return orderMapper.mapperToOrderPl(order);
    }

    @Override
    public List<GetMonthStatisticPl> searchByParams(String dateStart, String dateEnd) {
        log.debug("Edit order by params: dateStart= " + dateStart + ", dateEnd=" + dateEnd);
        Integer[] parseDateStart = (dateStart.isEmpty()) ? null : parseDate(dateStart);
        Integer[] parseDateEnd = (dateEnd.isEmpty()) ? null : parseDate(dateEnd);
        ZonedDateTime ds;
        ZonedDateTime de;

        if (parseDateStart != null && parseDateEnd != null) {
            ds = ZonedDateTime.of(parseDateStart[2], parseDateStart[1], parseDateStart[0], 0, 0, 0, 0, ZoneId.systemDefault());
            de = ZonedDateTime.of(parseDateEnd[2], parseDateEnd[1], parseDateEnd[0], 0, 0, 0, 0, ZoneId.systemDefault());

            return getGetMonthStatisticPls(Comparator.comparingInt(o -> o.getDateCreate().getNano()), repository.findAll(new OrderStatisticSpecification(ds, de)));
        }
        if (parseDateStart != null) {
            ds = ZonedDateTime.of(parseDateStart[2], parseDateStart[1], parseDateStart[0], 0, 0, 0, 0, ZoneId.systemDefault());
            de = ZonedDateTime.of(9999, 12, 30, 0, 0, 0, 0, ZoneId.systemDefault());

            return getGetMonthStatisticPls(Comparator.comparingInt(o -> o.getDateCreate().getNano()), repository.findAll(new OrderStatisticSpecification(ds, de)));
        }

        return getGetMonthStatisticPls(Comparator.comparingInt(o -> o.getDateCreate().getNano()), repository.findAll());

    }

    private List<GetMonthStatisticPl> getGetMonthStatisticPls(Comparator<Order> comparator, Collection<Order> collection) {
        List<Order> orderList = new ArrayList<>(collection);
        orderList.sort(comparator);

        return orderMapper.mapperToMonthStatisticPl(orderList).stream()
                .sorted((o1, o2) -> {
                    int compYear = o1.getYear() - o2.getYear();
                    if (compYear == 0) {
                        return o1.getMonth().getNum() - o2.getMonth().getNum();
                    }
                    return compYear;
                })
                .collect(Collectors.toList());
    }

}
