/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.filter.strategy.impl;


import com.glory.spark.core.component.filter.strategy.StrategyFilter;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.type.RetryStrategy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/12/3
 * @descprition :
 *
 */
@Component
@Order(99)
public class RetryAllStrategyFilter implements StrategyFilter {
	/**
	 * @param context
	 * @param <T>
	 * @return
	 */
	@Override
	public <T> boolean filter(SparkContext<T> context) {
		return true;
	}

	@Override
	public RetryStrategy supportStrategy() {
		return RetryStrategy.All;
	}
}
