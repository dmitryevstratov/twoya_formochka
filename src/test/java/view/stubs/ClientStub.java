package view.stubs;

import com.shepard1992.gmail.twoya_formochka.repository.model.Address;
import com.shepard1992.gmail.twoya_formochka.repository.model.Client;
import com.shepard1992.gmail.twoya_formochka.repository.model.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.model.Order;

import java.util.List;
import java.util.Set;

public class ClientStub {

    private ClientStub() {
    }

    public static Client getStub(Address address, List<Order> orderList, Set<Discount> discountSet) {
        return Client.builder()
                .id(1L)
                .firstName("Ivan")
                .secondName("Ivanovich")
                .lastName("Ivanov")
                .birthday("01.02.1990")
                .email("email")
                .telephone("13145")
                .address(address)
                .orders(orderList)
                .discounts(discountSet)
                .build();
    }

}
