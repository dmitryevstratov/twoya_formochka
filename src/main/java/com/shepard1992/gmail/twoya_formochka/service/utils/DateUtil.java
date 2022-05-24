package com.shepard1992.gmail.twoya_formochka.service.utils;

import com.shepard1992.gmail.twoya_formochka.view.model.enums.Month;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {

    public static ZonedDateTime converterDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US);
        LocalDateTime createLDT = LocalDate.parse(date, dtf).atStartOfDay();

        return ZonedDateTime.of(createLDT, ZoneId.systemDefault());
    }

    public static Integer[] parseDate(String date) {
        Integer[] array = new Integer[3];
        String[] strings = date.split("-");
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }

        return array;
    }

    public static Month parseDateToMonth(ZonedDateTime dateCreate) {
        return Month.values()[dateCreate.getMonthValue() - 1];
    }

}
