package com.shepard1992.gmail.twoya_formochka.repository.api;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer>, JpaSpecificationExecutor<Discount> {

    @Query("SELECT d FROM Discount d JOIN d.type t WHERE t.name = :typeName AND d.value = :value")
    Discount findByTypeNameAndValue(@Param("typeName") String typeName, @Param("value") Integer value);

}
