package com.shepard1992.gmail.twoya_formochka.repository.api;

import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, Integer> {

    DiscountType findByName(String type);

}
