package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;

import java.util.List;

public interface DiscountController {

    List<DiscountPl> getDiscounts();

    List<DiscountPl> searchByParams(Integer id, String type);

    DiscountPl getDiscountById(Integer id);

    DiscountPl addDiscount(DiscountPl discountPl);

    DiscountPl editDiscount(DiscountPl discountPl);

}
