/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.domain.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.spark.core.domain.type.RetryStrategy;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.domain.type.TaskStatus;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Convert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/18
 * @descprition :
 *
 */

public class SnapshotItemBo {
	private Long detailId;
	private Long snapshotId;
	private String sparkCode;
	private String type;
	private String taskCode;
	private String appName;
	private String contextPath;
	@Convert(converter = SourceFrom.SourceFromConverter.class)
	private SourceFrom source;
	@Convert(converter = TaskStatus.TaskStatusConverter.class)
	private TaskStatus status;
	private String traceId;
	private Long relatedId;
	private Long targetId;
	private String massage;
	@Convert(converter = RetryStrategy.RetryStrategyConverter.class)
	private RetryStrategy strategy;
	private Integer retryIndex;
	private LocalDateTime lastRetryTime;
	private LocalDateTime processDate;
	private Map<String, Object> properties;

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public SourceFrom getSource() {
		return source;
	}

	public void setSource(SourceFrom source) {
		this.source = source;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public RetryStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(RetryStrategy strategy) {
		this.strategy = strategy;
	}

	public Integer getRetryIndex() {
		return retryIndex;
	}

	public void setRetryIndex(Integer retryIndex) {
		this.retryIndex = retryIndex;
	}

	public LocalDateTime getLastRetryTime() {
		return lastRetryTime;
	}

	public void setLastRetryTime(LocalDateTime lastRetryTime) {
		this.lastRetryTime = lastRetryTime;
	}

	public LocalDateTime getProcessDate() {
		return processDate;
	}

	public void setProcessDate(LocalDateTime processDate) {
		this.processDate = processDate;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		if (null == this.properties){
			this.properties = new HashMap<>();
		}
		Optional.ofNullable(properties).ifPresent(this.properties::putAll);
	}

	@JsonIgnore
	public SnapshotItemBo addProperty(@Nonnull String key, @Nonnull Object value){
		if (null == this.properties){
			this.properties = new HashMap<>();
		}
		this.properties.put(key, value);
		return this;
	}
}
