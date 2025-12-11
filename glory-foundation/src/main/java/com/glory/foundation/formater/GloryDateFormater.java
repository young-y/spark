/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.formater;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/11/25
 * @descprition :
 *
 */

public class GloryDateFormater implements DateFormater{

	private final Map<String, SimpleDateFormat> formatMap = new LinkedHashMap<>(8);
	private final Map<String, DateTimeFormatter> formatterMap = new LinkedHashMap<>(8);
	@Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
	private String dateFormat;

	@PostConstruct
	public void initialize(){
		formatMap.put(dateFormat,new SimpleDateFormat(dateFormat));
		formatterMap.put(dateFormat,DateTimeFormatter.ofPattern(dateFormat));
	}
	/**
	 * @return
	 */
	@Override
	public DateTimeFormatter getFormatter(String patten) {
		if (!StringUtils.hasLength(patten)){
			patten = dateFormat;
		}
		return formatterMap.computeIfAbsent(patten, DateTimeFormatter::ofPattern);
	}

	/**
	 * @return
	 */
	@Override
	public DateFormat getFormat(String patten) {
		if (null == patten){
			patten = dateFormat;
		}
		return formatMap.computeIfAbsent(patten,SimpleDateFormat::new);
	}
}
