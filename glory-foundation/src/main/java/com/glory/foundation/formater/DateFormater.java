/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.formater;


import jakarta.annotation.Nonnull;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author : YY
 * @date : 2025/11/25
 * @descprition :
 *
 */

public interface DateFormater {

	DateTimeFormatter getFormatter(String patten);

	DateFormat getFormat(String patten);


	default Date parse(@Nonnull String dateStr){
		return parse(dateStr,getFormat(null));
	}

	default Date parse(@Nonnull String dateStr,@Nonnull String patten){
		return parse(dateStr,getFormat(patten));
	}

	default Date parse(@Nonnull String dateStr, DateFormat format){
		Assert.hasLength(dateStr,"date string is empty.");
		try {
			return format.parse(dateStr);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	default LocalDateTime parseLocal(@Nonnull String str){
		return parseLocal(str,getFormatter(null));
	}

	default LocalDateTime parseLocal(@Nonnull String str,String patten){
		return parseLocal(str,getFormatter(patten));
	}

	default LocalDateTime parseLocal(@Nonnull String str,DateTimeFormatter formatter){
		Assert.hasLength(str,"LocalDateTime string is empty.");
		return LocalDateTime.parse(str,formatter);
	}

	default LocalDate parseLocalDate(@Nonnull String str){
		return parseLocalDate(str,getFormatter(null));
	}

	default LocalDate parseLocalDate(@Nonnull String str,String patten){
		return parseLocalDate(str,getFormatter(patten));
	}

	default LocalDate parseLocalDate(@Nonnull String str, DateTimeFormatter formatter){
		Assert.hasLength(str,"LocalDateTime string is empty.");
		return LocalDate.parse(str,formatter);
	}

	default LocalTime parseLocalTime(@Nonnull String str){
		return parseLocalTime(str,getFormatter(null));
	}

	default LocalTime parseLocalTime(@Nonnull String str,String patten){
		return parseLocalTime(str,getFormatter(patten));
	}

	default LocalTime parseLocalTime(@Nonnull String str, DateTimeFormatter formatter){
		Assert.hasLength(str,"LocalTime string is empty.");
		return LocalTime.parse(str,formatter);
	}

}
