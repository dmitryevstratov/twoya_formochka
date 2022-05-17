package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.DiscountTypeService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.DiscountTypeController;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscountTypeControllerImpl implements DiscountTypeController {

    private final DiscountTypeService service;

    @Autowired
    public DiscountTypeControllerImpl(DiscountTypeService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/discount-types/search")
    public List<DiscountTypePl> searchByParams(@RequestParam(value = "type", required = false) String type) {
        return service.searchByParams(type);
    }

}
