package com.shepard1992.gmail.twoya_formochka.repository.specification;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderStatisticSpecification implements Specification<Order> {

    private final ZonedDateTime dateStart;
    private final ZonedDateTime dateEnd;

    public OrderStatisticSpecification(ZonedDateTime dateStart, ZonedDateTime dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public Predicate toPredicate(Root rt, CriteriaQuery qr, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (dateStart != null) {
            Predicate predicate = cb.between(rt.get("dateCreate"), dateStart, dateEnd);
            predicates.add(predicate);
        }
        if (dateEnd != null) {
            Predicate predicate = cb.between(rt.get("dateCreate"), dateStart, dateEnd);
            predicates.add(predicate);
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
