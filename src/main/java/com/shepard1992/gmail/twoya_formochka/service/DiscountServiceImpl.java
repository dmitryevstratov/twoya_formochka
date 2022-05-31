package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountTypeRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import com.shepard1992.gmail.twoya_formochka.repository.specification.DiscountTypeSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.DiscountMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository repository;
    private final DiscountTypeRepository discountTypeRepository;
    private final DiscountMapper mapper;
    private static final Logger log = Logger.getLogger(DiscountServiceImpl.class.getName());

    @Autowired
    public DiscountServiceImpl(DiscountRepository repository, DiscountTypeRepository discountTypeRepository, DiscountMapper mapper) {
        this.repository = repository;
        this.discountTypeRepository = discountTypeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DiscountPl> getDiscounts() {
        log.debug("Get all discounts");
        return repository.findAll().stream()
                .map(mapper::mapperToDiscountPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountPl> searchByParams(Integer id, String type) {
        log.debug("Get all discounts by params id=" + id + ", type=" + type);
        return repository.findAll(new DiscountTypeSpecification(id, type)).stream()
                .map(mapper::mapperToDiscountPl)
                .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public DiscountPl getDiscountById(Integer id) {
        log.debug("Get discount by id=" + id);
        return mapper.mapperToDiscountPl(repository.findById(id).get());
    }

    @Override
    public DiscountPl addDiscount(DiscountPl discountPl) {
        log.debug("Add discount: " + discountPl);
        return getDiscountPlForSave(discountPl);
    }

    @Override
    public DiscountPl editDiscount(DiscountPl discountPl) {
        log.debug("Edit discount by id=" + discountPl.getId());
        return getDiscountPlForSave(discountPl);
    }

    @Override
    public void deleteDiscountById(Integer id) {
        log.debug("Delete discount by id=" + id);
        Discount discount = repository.findById(id).get();

        discount.getOrders().forEach(order -> order.deleteDiscount(discount));
        discount.getClients().forEach(client -> client.deleteDiscount(discount));

        deleteDiscountType(discount);

        repository.deleteById(id);
    }

    private void deleteDiscountType(Discount discount) {
        DiscountType type = discount.getType();
        type.deleteDiscount(discount);
        List<Discount> discounts = type.getDiscounts();

        if (discounts != null && discounts.size() == 0) {
            discountTypeRepository.deleteById(type.getId());
        }
    }

    private DiscountPl getDiscountPlForSave(DiscountPl discountPl) {
        Discount discount = mapper.mapperToDiscount(discountPl);
        DiscountType type = discountTypeRepository.findByName(discount.getType().getName());

        if (type == null) {
            type = discountTypeRepository.save(discount.getType());
        }

        discount.setType(type);

        return mapper.mapperToDiscountPl(repository.save(discount));
    }

}
