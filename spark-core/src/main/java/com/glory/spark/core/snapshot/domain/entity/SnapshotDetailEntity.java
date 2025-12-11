/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.domain.entity;


import com.glory.data.jpa.domain.entity.DomainEntityWithDynamicFields;
import com.glory.spark.core.domain.type.RetryStrategy;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.domain.type.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author : YY
 * @date : 2025/11/11
 * @descprition :
 *
 */
@Entity
@Table(name = "T_SPARK_SNAPSHOT_DETAIL")
public class SnapshotDetailEntity extends DomainEntityWithDynamicFields {
	@Id
	@Column(name = "DETAIL_ID",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long detailId;
	@Column(name = "SNAPSHOT_ID",nullable = false)
	private Long snapshotId;
	@Column(name = "RELATED_ID")
	private Long relatedId;
	@Column(name = "SPARK_CODE",nullable = false)
	private String sparkCode;
	@Column(name = "TYPE",nullable = false)
	private String type;
	@Column(name = "TASK_CODE",nullable = false)
	private String taskCode;
	@Column(name = "TENANT")
	private String tenant;
	@Column(name = "TRACE_ID",nullable = false)
	private String traceId;
	@Column(name = "SOURCE",nullable = false)
	@Convert(converter = SourceFrom.SourceFromConverter.class)
	private SourceFrom source;
	@Column(name = "STATUS",nullable = false)
	@Convert(converter = TaskStatus.TaskStatusConverter.class)
	private TaskStatus status;
	@Column(name = "MESSAGE",length = 2000)
	private String message;
	@Column(name="TARGET_ID")
	private Long targetId;
	@Column(name = "STRATEGY")
	@Convert(converter = RetryStrategy.RetryStrategyConverter.class)
	private RetryStrategy strategy;
	@Column(name = "RETRY_INDEX")
	private int retryIndex = 0;
	@Column(name = "LAST_RETRY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime lastRetryTime;

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

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
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

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public RetryStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(RetryStrategy strategy) {
		this.strategy = strategy;
	}

	public int getRetryIndex() {
		return retryIndex;
	}

	public void setRetryIndex(int retryIndex) {
		this.retryIndex = retryIndex;
	}

	public LocalDateTime getLastRetryTime() {
		return lastRetryTime;
	}

	public void setLastRetryTime(LocalDateTime lastRetryTime) {
		this.lastRetryTime = lastRetryTime;
	}

}
