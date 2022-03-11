package com.example.demo.date;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateUtil {

    public static OffsetDateTime parse(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(localDateTime);
        return OffsetDateTime.of(localDateTime, offset);
    }

}
