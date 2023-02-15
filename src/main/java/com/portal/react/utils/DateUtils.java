package com.portal.react.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date now() {
        LocalDate localDateTIme = LocalDate.now(ZoneId.of("Asia/Seoul"));

        Date result = java.sql.Date.valueOf(localDateTIme);

        return result;
    }
}
