/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.service.impl;


import com.glory.spark.core.annotation.SparkCondition;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author : YY
 * @date : 2025/11/27
 * @descprition :
 *
 */

public class TestSparkCondition implements SparkCondition {
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * @param argus
	 * @return
	 */
	@Override
	public boolean match(@Nonnull Method method, Object[] argus) {
		logger.info(">> spark method {}",method.getName());
		return method.getName().equals("savePerson");
	}
}
