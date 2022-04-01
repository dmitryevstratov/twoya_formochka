package view.stubs;

import com.shepard1992.gmail.twoya_formochka.view.model.AddressPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ClientPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.OrderPl;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ClientPlStub {

    private ClientPlStub() {
    }

    public static ClientPl getStub(AddressPl addressPl, List<OrderPl> orderPlList, Set<DiscountPl> discountPlSet) {
        return ClientPl.builder()
                .id(1L)
                .firstName("Ivan")
                .secondName("Ivanovich")
                .lastName("Ivanov")
                .birthday(new Date())
                .email("email")
                .telephone("13145")
                .address(addressPl)
                .orders(orderPlList)
                .discounts(discountPlSet)
                .build();
    }

}
