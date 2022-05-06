package view.stubs;

import com.shepard1992.gmail.twoya_formochka.view.model.FilterOrderPl;

public class FilterOrderPlStub {

    private FilterOrderPlStub() {
    }

    public static FilterOrderPl getStub() {
        return FilterOrderPl.builder()
                .id(1)
                .firstName("Kex")
                .lastName("Shmexov")
                .dateCreate("28-04-2022")
                .dateClosed("03-05-2022")
                .selectedStatus("CREATED")
                .priceMin("0")
                .priceMax("200")
                .count("3")
                .build();
    }
}
