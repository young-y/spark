/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.utils;


import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */

public final  class DateUtils {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    private final static Map<String, SimpleDateFormat> FORMATS = new HashMap<>(8);
    private final static Map<String, DateTimeFormatter> FORMATTERS = new HashMap<>(8);
    private DateUtils(){}

    private static SimpleDateFormat getFormat(String format){
        if (null == format){
            format = DEFAULT_FORMAT;
        }
        return FORMATS.computeIfAbsent(format, SimpleDateFormat::new);
    }

    private static DateTimeFormatter getFormatter(String format){
        if (null == format){
            format = DEFAULT_FORMAT;
        }
        return FORMATTERS.computeIfAbsent(format, DateTimeFormatter::ofPattern);
    }

    public static Date parse(@Nonnull String dateStr){
        return parse(dateStr,DEFAULT_FORMAT);
    }

    public static Date parse(@Nonnull String dateStr,String format){
        Assert.hasLength(dateStr,"date string is empty.");
        SimpleDateFormat dateFormat = getFormat(format);
        try {
            return dateFormat.parse(dateStr);
        }catch (Exception e){
            LOGGER.debug(">> parse Date({}) exception :",dateStr,e);
        }
        return null;
    }

    public static LocalDateTime parseLocal(@Nonnull String str){
        return parseLocal(str,DEFAULT_FORMAT);
    }

    public static LocalDateTime parseLocal(@Nonnull String str,String format){
        Assert.hasLength(str,"LocalDateTime string is empty.");
        DateTimeFormatter formatter = getFormatter(format);
        return LocalDateTime.parse(str,formatter);
    }

    public static LocalDate parseLocalDate(@Nonnull String str){
        return parseLocalDate(str,DEFAULT_FORMAT_DATE);
    }

    public static LocalDate parseLocalDate(@Nonnull String str, String format){
        Assert.hasLength(str,"LocalDateTime string is empty.");
        DateTimeFormatter formatter = getFormatter(format);
        return LocalDate.parse(str,formatter);
    }

    public static LocalDate toLocalDate(@Nonnull Date date){
        return toLocalDate(date,ZoneId.systemDefault());
    }

    public static LocalDate toLocalDate(@Nonnull Date date,ZoneId zoneId){
        return date.toInstant().atZone(zoneId).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(@Nonnull Date date){
        return toLocalDateTime(date,ZoneId.systemDefault());
    }

    public static LocalDateTime toLocalDateTime(@Nonnull Date date,ZoneId zoneId){
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public static Date toDate(@Nonnull LocalDate localDate){
        return toDate(localDate,ZoneId.systemDefault());
    }

    public static Date toDate(@Nonnull LocalDate localDate, ZoneId zoneId){
        return Date.from(localDate.atStartOfDay(zoneId).toInstant());
    }

    public static Date toDate(@Nonnull LocalDateTime localDateTime){
        return toDate(localDateTime,ZoneId.systemDefault());
    }
    public static Date toDate(@Nonnull LocalDateTime localDateTime,ZoneId zoneId){
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    public static long calculateDays(@Nonnull LocalDateTime startTime,@Nonnull LocalDateTime endTime){
        Assert.isTrue(endTime.isAfter(startTime)," Start DataTime greater than end DateTime.");
        return ChronoUnit.DAYS.between(startTime,endTime);
    }

    public static long calculateDays(@Nonnull Date start,@Nonnull Date end){
        Assert.isTrue(end.after(start),"Start date greater than end date.");
        long times = end.getTime()-start.getTime();
        return Math.abs(times / (24 * 60 * 60 * 1000));
    }

    public static Date offsetDays(@Nonnull Date date,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }

    public static Date offsetMonths(@Nonnull Date date,int months){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,months);
        return calendar.getTime();
    }

    public static Date offsetYears(@Nonnull Date date,int years){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR,years);
        return calendar.getTime();
    }

    public static LocalDate offsetDays(@Nonnull LocalDate localDate,int days){
        return localDate.plusDays(days);
    }

    public static LocalDate offsetMonths(@Nonnull LocalDate localDate,int months){
        return localDate.plusMonths(months);
    }


    public static LocalDate offsetYears(@Nonnull LocalDate localDate,int years){
        return localDate.plusYears(years);
    }


    public static LocalDateTime offsetDays(@Nonnull LocalDateTime dateTime,int days){
        return dateTime.plusDays(days);
    }

    public static LocalDateTime offsetMonths(@Nonnull LocalDateTime dateTime,int months){
        return dateTime.plusMonths(months);
    }

    public static LocalDateTime offsetYears(@Nonnull LocalDateTime dateTime,int years){
        return dateTime.plusYears(years);
    }

    public static boolean isEffective(LocalDateTime start,LocalDateTime end,LocalDateTime effective){
        return isEffective(start,end,effective,false,true);
    }

    public static boolean isEffective(LocalDateTime start,LocalDateTime end,LocalDateTime effective,boolean includeStart,boolean includeEnd){
        if (null == start && null ==end){
            return true;
        }
        if (null == effective){
            effective = LocalDateTime.now();
        }
        if (null != start && null != end){
            return (effective.isAfter(start) || (includeStart &&effective.equals(start)))
                    && (effective.isBefore(end) || (includeEnd &&effective.equals(end)));
        }
        if (null != start){
            return  effective.isAfter(start);
        }
        return effective.isBefore(end) || effective.equals(end);
    }

    public static boolean isEffective(Date start,Date end ,Date effective){
        return isEffective(start,end,effective,false,true);
    }

    public static boolean isEffective(Date start,Date end ,Date effective,boolean includeStart,boolean includeEnd){
        if (null == start && null ==end){
            return true;
        }
        if (null == effective){
            effective = new Date();
        }
        if (null != start && null != end){
            return (effective.after(start) || (includeStart &&effective.equals(start)))
                    && (effective.before(end) || (includeEnd &&effective.equals(end)));
        }
        if (null != start){
            return  effective.after(start);
        }
        return effective.before(end) || effective.equals(end);
    }

}
