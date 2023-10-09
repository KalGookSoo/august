package com.kalgookso.august.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 컨버터
 */
public class Converter {

    /**
     * LocalDateTime으로 변환하여 반환한다.
     * @param localDateTime 변환 대상
     * @return 변환된 LocalDateTime
     */
    public LocalDateTime asLocalDateTime(Object localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        if (localDateTime instanceof LocalDateTime) {
            return (LocalDateTime) localDateTime;
        }
        if (localDateTime instanceof String) {
            return LocalDateTime.parse((String) localDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        return null;
    }

    /**
     * 문자열로 변환하여 반환한다.
     * @param object 변환 대상
     * @return 변환된 문자열
     */
    public String asString(Object object) {
        if (object == null) {
            return null;
        }
        return object.toString();
    }

}
