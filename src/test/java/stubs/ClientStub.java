package stubs;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Address;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Client;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Order;

import java.util.List;

public class ClientStub {

    private ClientStub() {
    }

    public static Client getStub(Address address, List<Order> orderList, List<Discount> discountSet) {
        return Client.builder()
                .id(1)
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
