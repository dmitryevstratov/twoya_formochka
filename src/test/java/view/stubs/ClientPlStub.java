package view.stubs;

import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;

import java.util.List;

public class ClientPlStub {

    private ClientPlStub() {
    }

    public static ClientPl getStub(AddressPl addressPl, List<OrderPl> orderPlList, List<DiscountPl> discountPlSet) {
        return ClientPl.builder()
                .id(1L)
                .firstName("Ivan")
                .secondName("Ivanovich")
                .lastName("Ivanov")
                .birthday("01.02.1990")
                .email("email")
                .telephone("13145")
                .address(addressPl)
                .orders(orderPlList)
                .discounts(discountPlSet)
                .build();
    }

}
