package view.stubs;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Address;

public class AddressStub {

    private AddressStub() {
    }

    public static Address getStub() {
        return Address.builder()
                .id(1)
                .country("Беларусь")
                .region("Гродненская")
                .locality("Гродно")
                .room(20)
                .street("Ленина 10")
                .index(230041)
                .additionalInfo("Европочта")
                .build();
    }

}
