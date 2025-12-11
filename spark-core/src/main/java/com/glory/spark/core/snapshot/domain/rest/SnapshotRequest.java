/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.domain.rest;


import com.glory.data.jpa.domain.page.PageRequest;
import com.glory.spark.core.domain.type.RetryStrategy;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.domain.type.TaskStatus;
import jakarta.persistence.Convert;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/17
 * @descprition :
 *
 */

public class SnapshotRequest extends PageRequest {
	private Long snapshotId;
	private String sparkCode;
	private String appName;
	@Convert(converter = TaskStatus.TaskStatusConverter.class)
	private TaskStatus status;
	private List<Integer> statuses;
	@Convert(converter = SourceFrom.SourceFromConverter.class)
	private SourceFrom source;
	@Convert(converter = RetryStrategy.RetryStrategyConverter.class)
	private RetryStrategy strategy;
	private String serialId;
	private String tenant;
	private String type;
	private String taskCode;
	private Long detailId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public SnapshotRequest() {
	}

	public Long getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(Long snapshotId) {
		this.snapshotId = snapshotId;
	}

	public String getSparkCode() {
		return sparkCode;
	}

	public void setSparkCode(String sparkCode) {
		this.sparkCode = sparkCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public List<Integer> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Integer> statuses) {
		this.statuses = statuses;
	}

	public SourceFrom getSource() {
		return source;
	}

	public void setSource(SourceFrom source) {
		this.source = source;
	}

	public RetryStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(RetryStrategy strategy) {
		this.strategy = strategy;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
}
