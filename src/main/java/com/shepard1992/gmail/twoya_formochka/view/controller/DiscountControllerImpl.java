package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.view.controller.api.DiscountController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ModelAndViewController;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiscountControllerImpl implements ModelAndViewController, DiscountController {

    @Override
    @GetMapping("/discounts.html")
    public ModelAndView getView(Model model) {
        return new ModelAndView("discounts");
    }

    @Override
    @GetMapping("/discounts")
    public List<DiscountPl> getDiscounts() {
        return new ArrayList<>();
    }

}
