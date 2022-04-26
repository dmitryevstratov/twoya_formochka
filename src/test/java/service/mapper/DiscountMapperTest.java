package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Discount;
import com.shepard1992.gmail.twoya_formochka.repository.entity.DiscountType;
import com.shepard1992.gmail.twoya_formochka.service.mapper.DiscountMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountPl;
import com.shepard1992.gmail.twoya_formochka.view.model.DiscountTypePl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;
import view.stubs.DiscountPlStub;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class DiscountMapperTest {

    @Autowired
    private DiscountMapper discountMapper;

    @Test
    public void test_mapperToDiscount(){
        DiscountPl discountPl = DiscountPlStub.getStub(DiscountTypePl.builder()
                .id(10L)
                .name("Новый год")
                .build());

        Discount discount = discountMapper.mapperToDiscount(discountPl);

        assertEquals(discountPl.getType().getId(), discount.getType().getId());
        assertEquals(discountPl.getType().getName(), discount.getType().getName());
        assertEquals(discountPl.getId(), discount.getId());
        assertEquals(discountPl.getValue(), discount.getValue());
    }

    @Test
    public void test_mapperToDiscountPl(){
        Discount discount = Discount.builder()
                .id(1L)
                .value(20)
                .type(DiscountType.builder().id(2L).name("ДР").build())
                .build();

        DiscountPl discountPl = discountMapper.mapperToDiscountPl(discount);

        assertEquals(discount.getType().getId(), discountPl.getType().getId());
        assertEquals(discount.getType().getName(), discountPl.getType().getName());
        assertEquals(discount.getId(), discountPl.getId());
        assertEquals(discount.getValue(), discountPl.getValue());
    }

}
