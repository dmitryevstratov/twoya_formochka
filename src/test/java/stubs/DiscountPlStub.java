package stubs;

import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;

public class DiscountPlStub {

    private DiscountPlStub() {
    }

    public static DiscountPl getStub(DiscountTypePl discountTypePl) {
        return DiscountPl.builder()
                .id(1)
                .value(20)
                .type(discountTypePl)
                .build();
    }

}
