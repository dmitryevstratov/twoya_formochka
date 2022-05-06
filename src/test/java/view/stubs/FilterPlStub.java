package view.stubs;

import com.shepard1992.gmail.twoya_formochka.view.model.FilterClientPl;

public class FilterPlStub {

    private FilterPlStub() {
    }

    public static FilterClientPl getStub() {
        return FilterClientPl.builder()
                .id(1)
                .firstName("Ivan")
                .secondName("Ivanovich")
                .lastName("Ivanov")
                .birthday("01.02.1990")
                .email("email")
                .telephone("13145")
                .build();
    }

}
