package com.shepard1992.gmail.twoya_formochka.repository.api;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
}
