package com.shepard1992.gmail.twoya_formochka.repository.specification;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DiscountSpecification implements Specification<Discount> {

    private final Integer id;
    private final String type;

    public DiscountSpecification(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public Predicate toPredicate(Root rt, CriteriaQuery qr, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            Predicate predicate = cb.equal(rt.get("id"), id);
            predicates.add(predicate);
        }
        if (type != null && !type.isEmpty()) {
            Predicate predicate = cb.equal(rt.get("type").get("name"), type);
            predicates.add(predicate);
        }


        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
