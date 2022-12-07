package com.gt.utils;

import com.gt.exception.GTException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConvertor {

    private DateConvertor() {
        throw new GTException("99","Utility class");
    }

    public static LocalDate convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
