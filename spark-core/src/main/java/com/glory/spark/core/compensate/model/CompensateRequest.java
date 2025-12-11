/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.compensate.model;


import com.glory.spark.core.domain.type.RetryStrategy;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import jakarta.persistence.Convert;

/**
 * @author : YY
 * @date : 2025/12/2
 * @descprition :
 *
 */

public class CompensateRequest {
	private SnapshotBo snapshot;
	@Convert(converter = RetryStrategy.RetryStrategyConverter.class)
	private RetryStrategy strategy = RetryStrategy.Fail;
	private boolean sync = true;

	public SnapshotBo getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(SnapshotBo snapshot) {
		this.snapshot = snapshot;
	}

	public RetryStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(RetryStrategy strategy) {
		this.strategy = strategy;
	}

	public boolean isSync() {
		return sync;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}
}
