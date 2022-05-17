package com.shepard1992.gmail.twoya_formochka.repository.api;

import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, Integer>, JpaSpecificationExecutor<DiscountType> {

    DiscountType findByName(String type);

}
