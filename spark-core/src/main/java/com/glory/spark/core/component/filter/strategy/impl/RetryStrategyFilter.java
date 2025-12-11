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
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.domain.type.RetryStrategy;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/3
 * @descprition :
 *
 */
@Component
@Order(100)
public class RetryStrategyFilter implements StrategyFilter {
	/**
	 * @param context
	 * @param <T>
	 * @return
	 */
	@Override
	public <T> boolean filter(SparkContext<T> context) {
		List<SnapshotDetailBo> details = context.getSnapshotInfo().getDetails();
		SparkTaskDesc taskDesc = context.getTaskDesc();
		return details.stream()
				.anyMatch(d->d.getStatus() != TaskStatus.Compensate
						&& d.getType().equals(taskDesc.getType())
						&& d.getTaskCode().equals(taskDesc.getTaskCode()));
	}

	@Override
	public RetryStrategy supportStrategy() {
		return RetryStrategy.Retry;
	}
}
