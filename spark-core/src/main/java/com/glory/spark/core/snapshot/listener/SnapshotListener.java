/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.listener;


import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;

/**
 * @author : YY
 * @date : 2025/11/3
 * @descprition :
 *
 */

public interface SnapshotListener {

	<T> void capture(SparkContext<T> context);

	default <T> void captureTask(SparkContext<T> context) {
	}

	default <T> void update(SnapshotBo bo) {
	}

	default void updateTask(SparkTaskDesc taskDesc) {

	}
}
