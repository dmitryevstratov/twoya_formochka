package service.utils;

import com.shepard1992.gmail.twoya_formochka.service.utils.DateUtil;
import com.shepard1992.gmail.twoya_formochka.view.model.enums.Month;
import org.junit.Test;

import java.time.ZoneId;
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

    @Test
    public void test_when_call_parseDate_then_return_null() {
        Integer[] integers = DateUtil.parseDate("01-05-2020");

        assertEquals(Integer.valueOf(1), integers[0]);
        assertEquals(Integer.valueOf(5), integers[1]);
        assertEquals(Integer.valueOf(2020), integers[2]);
    }

    @Test
    public void test_when_call_parseDateToMonth_then_return_null() {
        Month month = DateUtil.parseDateToMonth(ZonedDateTime.of(1990, 3, 10, 1, 1, 1, 1, ZoneId.systemDefault()));

        assertEquals("Март", month.getName());
    }

}
