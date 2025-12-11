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
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : YY
 * @date : 2025/12/4
 * @descprition :
 * If this condition is met, snapshots will not be recorded
 * eg env property:spark.snapshot.enabled = false,default is true
 */

public class NoneSnapshotListener implements SnapshotListener{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * @param context
	 * @param <T>
	 */
	@Override
	public <T> void capture(SparkContext<T> context) {
		logger.info(">> None snapshot capture.");
	}

	/**
	 * @param context
	 * @param <T>
	 */
	@Override
	public <T> void captureTask(SparkContext<T> context) {
		logger.debug(">> None snapshot captureTask.");
	}

	@Override
	public void update(SnapshotBo bo) {
		logger.debug(">> None snapshot updateSnapshot.");
	}

	/**
	 * @param taskDesc
	 */
	@Override
	public void updateTask(SparkTaskDesc taskDesc) {
		logger.debug(">> None snapshot updateSnapshotDetail.");
	}
}
