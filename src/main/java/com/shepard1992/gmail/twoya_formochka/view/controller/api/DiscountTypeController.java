package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;

import java.util.List;

public interface DiscountTypeController {

    List<DiscountTypePl> searchByParams(String type);

}
