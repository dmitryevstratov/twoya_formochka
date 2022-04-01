package view.stubs;

import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;

public class AddressPlStub {

    private AddressPlStub() {
    }

    public static AddressPl getStub() {
        return AddressPl.builder()
                .id(1L)
                .country("Беларусь")
                .region("Гродненская")
                .locality("Гродно")
                .room(20)
                .street("Ленина 10")
                .index(230041)
                .build();
    }

}
