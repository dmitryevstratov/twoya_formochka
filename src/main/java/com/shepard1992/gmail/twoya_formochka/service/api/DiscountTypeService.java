package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;

import java.util.List;

public interface DiscountTypeService {

    List<DiscountTypePl> searchByParams(String type);

}
