package view.stubs;

import com.shepard1992.gmail.twoya_formochka.repository.model.Filter;
import com.shepard1992.gmail.twoya_formochka.view.model.FilterPl;

public class FilterPlStub {

    private FilterPlStub() {
    }

    public static FilterPl getStub() {
        return FilterPl.builder()
                .id(1L)
                .firstName("Ivan")
                .secondName("Ivanovich")
                .lastName("Ivanov")
                .birthday("01.02.1990")
                .email("email")
                .telephone("13145")
                .build();
    }

}
