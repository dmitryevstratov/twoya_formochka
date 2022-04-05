package com.shepard1992.gmail.twoya_formochka.repository.specification;

import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.repository.model.Filter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClientSpecification implements Specification<Client> {

    private final Filter filter;

    public ClientSpecification(Filter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root rt, CriteriaQuery qr, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getId() != null) {
            Predicate predicate = cb.equal(rt.get("id"), filter.getId());
            predicates.add(predicate);
        }
        if (!filter.getFirstName().equals("") && filter.getFirstName() != null) {
            Predicate predicate = cb.equal(rt.get("firstName"), filter.getFirstName());
            predicates.add(predicate);
        }
        if (!filter.getLastName().equals("") && filter.getLastName() != null) {
            Predicate predicate = cb.equal(rt.get("lastName"), filter.getLastName());
            predicates.add(predicate);
        }
        if (!filter.getSecondName().equals("") && filter.getSecondName() != null) {
            Predicate predicate = cb.equal(rt.get("secondName"), filter.getSecondName());
            predicates.add(predicate);
        }
        if (!filter.getBirthday().equals("") && filter.getBirthday() != null) {
            Predicate predicate = cb.equal(rt.get("birthday"), filter.getBirthday());
            predicates.add(predicate);
        }
        if (!filter.getEmail().equals("") && filter.getEmail() != null) {
            Predicate predicate = cb.equal(rt.get("email"), filter.getEmail());
            predicates.add(predicate);
        }
        if (!filter.getTelephone().equals("") && filter.getTelephone() != null) {
            Predicate predicate = cb.equal(rt.get("telephone"), filter.getTelephone());
            predicates.add(predicate);
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
