package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.DiscountTypeRepository;
import com.shepard1992.gmail.twoya_formochka.service.api.DiscountTypeService;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountTypeServiceImpl implements DiscountTypeService {

    private final DiscountTypeRepository repository;

    @Autowired
    public DiscountTypeServiceImpl(DiscountTypeRepository discountTypeRepository) {
        this.repository = discountTypeRepository;
    }

    @Override
    public List<DiscountTypePl> searchByParams(String type) {
        if (type.isEmpty()) {
            return repository.findAll().stream()
                    .map(tp -> DiscountTypePl.builder()
                            .id(tp.getId())
                            .name(tp.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        return repository.findAllByName(type).stream()
                .map(tp -> DiscountTypePl.builder()
                        .id(tp.getId())
                        .name(tp.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
