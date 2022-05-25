package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.dto.Filter;
import com.shepard1992.gmail.twoya_formochka.repository.dto.FilterOrder;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterOrderPl;
import org.springframework.stereotype.Component;

import static com.shepard1992.gmail.twoya_formochka.service.utils.DateUtil.converterDate;

@Component
public class FilterMapper {

    public Filter mapperToFilter(FilterClientPl filterClientPl) {
        return Filter.builder()
                .id(filterClientPl.getId())
                .firstName(filterClientPl.getFirstName())
                .lastName(filterClientPl.getLastName())
                .secondName(filterClientPl.getSecondName())
                .birthday(filterClientPl.getBirthday())
                .email(filterClientPl.getEmail())
                .telephone(filterClientPl.getTelephone())
                .discountName(filterClientPl.getDiscountName())
                .build();
    }

    public FilterOrder mapperToOrderFilter(FilterOrderPl filterOrderPl) {

        return FilterOrder.builder()
                .id(filterOrderPl.getId())
                .firstName(filterOrderPl.getFirstName())
                .lastName(filterOrderPl.getLastName())
                .dateCreate(converterDate(filterOrderPl.getDateCreate()))
                .dateClosed(converterDate(filterOrderPl.getDateClosed()))
                .selectedStatus(filterOrderPl.getSelectedStatus())
                .priceMax(filterOrderPl.getPriceMax())
                .priceMin(filterOrderPl.getPriceMin())
                .count(filterOrderPl.getCount())
                .build();
    }

}
