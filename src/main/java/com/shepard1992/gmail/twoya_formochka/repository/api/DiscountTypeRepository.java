package com.shepard1992.gmail.twoya_formochka.repository.api;

import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, Integer> {

    DiscountType findByName(String type);

    List<DiscountType> findAllByName(String type);

}
