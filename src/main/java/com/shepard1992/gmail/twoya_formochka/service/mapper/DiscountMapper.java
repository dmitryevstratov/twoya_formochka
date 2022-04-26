package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {

    public Discount mapperToDiscount(DiscountPl discountPl) {
        return Discount.builder()
                .id(discountPl.getId())
                .value(discountPl.getValue())
                .type(DiscountType.builder()
                        .id(discountPl.getType().getId())
                        .name(discountPl.getType().getName())
                        .build())
                .build();
    }

    public DiscountPl mapperToDiscountPl(Discount discount) {
        return DiscountPl.builder()
                .id(discount.getId())
                .value(discount.getValue())
                .type(DiscountTypePl.builder()
                        .id(discount.getType().getId())
                        .name(discount.getType().getName())
                        .build())
                .build();
    }

}
