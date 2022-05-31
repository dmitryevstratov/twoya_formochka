package com.shepard1992.gmail.twoya_formochka.repository.api;

import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountTypeRepository extends JpaRepository<DiscountType, Integer> {

    DiscountType findByName(String type);

    List<DiscountType> findAllByName(String type);

}
