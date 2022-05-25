package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.DiscountService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.DiscountController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class DiscountControllerImpl implements ModelAndViewController, DiscountController {

    private final DiscountService service;

    @Autowired
    public DiscountControllerImpl(DiscountService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/discounts.html")
    public ModelAndView getView(Model model) {
        return new ModelAndView("discounts");
    }

    @Override
    @GetMapping("/discounts")
    public List<DiscountPl> getDiscounts() {
        return service.getDiscounts();
    }

    @Override
    @GetMapping("/discounts/search")
    public List<DiscountPl> searchByParams(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "type", required = false) String type) {
        return service.searchByParams(id, type);
    }

    @Override
    @GetMapping("/discounts/{id}")
    public DiscountPl getDiscountById(@PathVariable Integer id) {
        return service.getDiscountById(id);
    }

    @Override
    @PostMapping("/discounts/create")
    public DiscountPl addDiscount(@RequestBody DiscountPl discountPl) {
        return service.addDiscount(discountPl);
    }

    @Override
    @PutMapping("/discounts/edit")
    public DiscountPl editDiscount(@RequestBody DiscountPl discountPl) {
        return service.editDiscount(discountPl);
    }

    @Override
    @DeleteMapping("/discounts/{id}")
    public void deleteDiscountById(@PathVariable Integer id) {
        service.deleteDiscountById(id);
    }


}
