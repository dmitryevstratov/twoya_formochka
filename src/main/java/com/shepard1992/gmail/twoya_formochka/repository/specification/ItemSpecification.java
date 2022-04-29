package com.shepard1992.gmail.twoya_formochka.repository.specification;

import com.shepard1992.gmail.twoya_formochka.repository.dto.FilterItem;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecification implements Specification<Item> {

    private final FilterItem filter;

    public ItemSpecification(FilterItem filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root rt, CriteriaQuery qr, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getId() != null) {
            Predicate predicate = cb.equal(rt.get("id"), filter.getId());
            predicates.add(predicate);
        }
        if (filter.getName() != null && !filter.getName().equals("")) {
            Predicate predicate = cb.equal(rt.get("name"), filter.getName());
            predicates.add(predicate);
        }
        if (filter.getType() != null && !filter.getType().equals("")) {
            Predicate predicate = cb.equal(rt.get("type").get("name"), filter.getType());
            predicates.add(predicate);
        }
        if (filter.getCategory() != null && !filter.getCategory().equals("")) {
            Predicate predicate = cb.equal(rt.get("category").get("name"), filter.getCategory());
            predicates.add(predicate);
        }
        if (filter.getSize() != null) {
            Predicate predicate = cb.equal(rt.get("size"), filter.getSize());
            predicates.add(predicate);
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
