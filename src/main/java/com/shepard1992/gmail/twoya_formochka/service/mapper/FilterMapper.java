package com.shepard1992.gmail.twoya_formochka.service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.dto.Filter;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterPl;
import org.springframework.stereotype.Component;

@Component
public class FilterMapper {

    public Filter mapperToFilter(FilterPl filterPl) {
        return Filter.builder()
                .id(filterPl.getId())
                .firstName(filterPl.getFirstName())
                .lastName(filterPl.getLastName())
                .secondName(filterPl.getSecondName())
                .birthday(filterPl.getBirthday())
                .email(filterPl.getEmail())
                .telephone(filterPl.getTelephone())
                .build();
    }

}
