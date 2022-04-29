package service.utils;

import com.shepard1992.gmail.twoya_formochka.service.utils.DateUtil;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    @Test
    public void test_converterDate() {
        ZonedDateTime zonedDateTime = DateUtil.converterDate("11-04-1996");

        assertEquals("1996-04-11T00:00+03:00[Europe/Minsk]", zonedDateTime.toString());
    }

    @Test
    public void test_when_call_converterDate_with_date_empty_then_return_null() {
        ZonedDateTime zonedDateTime = DateUtil.converterDate(null);

        assertEquals(null, zonedDateTime);
    }

}
