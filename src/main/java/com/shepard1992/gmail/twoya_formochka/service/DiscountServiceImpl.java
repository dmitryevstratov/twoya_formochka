package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.specification.DiscountSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.DiscountMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository repository;

    private final DiscountMapper mapper;

    @Autowired
    public DiscountServiceImpl(DiscountRepository repository, DiscountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DiscountPl> getDiscounts() {
        return repository.findAll().stream()
                .map(mapper::mapperToDiscountPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountPl> searchByParams(Integer id, String type) {
        return repository.findAll(new DiscountSpecification(id, type)).stream()
                .map(mapper::mapperToDiscountPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public DiscountPl getDiscountById(Integer id) {
        return mapper.mapperToDiscountPl(repository.getById(id));
    }

    @Override
    public DiscountPl addDiscount(DiscountPl discountPl) {
        Discount discount = repository.save(mapper.mapperToDiscount(discountPl));
        return mapper.mapperToDiscountPl(discount);
    }

    @Override
    public DiscountPl editDiscount(DiscountPl discountPl) {
        Discount discount = repository.save(mapper.mapperToDiscount(discountPl));
        return mapper.mapperToDiscountPl(discount);
    }

}
