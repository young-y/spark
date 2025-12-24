/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.desensitize.logback;


import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.glory.foundation.desensitize.DesensitizeHelper;
import org.slf4j.helpers.MessageFormatter;

import java.util.Arrays;
import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/17
 * @descprition :
 *
 */

public class DesensitizeConverter extends ClassicConverter {
	/**
	 * @param event
	 * @return
	 */
	@Override
	public String convert(ILoggingEvent event) {
		Object[] argumentArray = event.getArgumentArray();
		if (null == argumentArray || argumentArray.length == 0){
			return event.getFormattedMessage();
		}
		List<Object> argus = Arrays.stream(argumentArray).map(DesensitizeHelper::desensitizeToJson).toList();
		return MessageFormatter.format(event.getMessage(),argus.toArray()).getMessage();
	}

}
