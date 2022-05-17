package com.shepard1992.gmail.twoya_formochka.repository.api;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount, Integer>, JpaSpecificationExecutor<Discount> {

}
