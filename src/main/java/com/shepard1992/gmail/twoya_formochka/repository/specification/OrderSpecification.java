package com.shepard1992.gmail.twoya_formochka.repository.specification;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;
import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification implements Specification<Order> {

    private final FilterOrder filter;

    public OrderSpecification(FilterOrder filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root rt, CriteriaQuery qr, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getId() != null) {
            Predicate predicate = cb.equal(rt.get("id"), filter.getId());
            predicates.add(predicate);
        }
        if (filter.getFirstName() != null && !filter.getFirstName().equals("")) {
            Predicate predicate = cb.equal(rt.get("client").get("firstName"), filter.getFirstName());
            predicates.add(predicate);
        }
        if (filter.getLastName() != null && !filter.getLastName().equals("")) {
            Predicate predicate = cb.equal(rt.get("client").get("lastName"), filter.getLastName());
            predicates.add(predicate);
        }
        if (filter.getDateCreate() != null && !filter.getDateCreate().toString().equals("")) {
            Predicate predicate = cb.equal(rt.get("dateCreate"), filter.getDateCreate());
            predicates.add(predicate);
        }
        if (filter.getDateClosed() != null && !filter.getDateClosed().toString().equals("")) {
            Predicate predicate = cb.equal(rt.get("dateClosed"), filter.getDateClosed());
            predicates.add(predicate);
        }
        if (filter.getSelectedStatus() != null && !filter.getSelectedStatus().equals("")) {
            Predicate predicate = cb.equal(rt.get("status"), StatusOrder.valueOf(filter.getSelectedStatus()));
            predicates.add(predicate);
        }
        if (filter.getPriceMin() != null && !filter.getPriceMin().equals("")
                && filter.getPriceMax() != null && !filter.getPriceMax().equals("")) {
            Predicate predicate = cb.between(rt.get("totalPrice"), Double.parseDouble(filter.getPriceMin()), Double.parseDouble(filter.getPriceMax()));
            predicates.add(predicate);
        } else if (filter.getPriceMin() != null && !filter.getPriceMin().equals("")) {
            Predicate predicate = cb.between(rt.get("totalPrice"), Double.parseDouble(filter.getPriceMin()), Double.parseDouble("9999"));
            predicates.add(predicate);
        } else if (filter.getPriceMax() != null && !filter.getPriceMax().equals("")) {
            Predicate predicate = cb.between(rt.get("totalPrice"), Double.parseDouble("0"), Double.parseDouble(filter.getPriceMax()));
            predicates.add(predicate);
        }
        if (filter.getCount() != null && !filter.getCount().equals("")) {
            Predicate predicate = cb.equal(rt.get("count"), filter.getCount());
            predicates.add(predicate);
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
