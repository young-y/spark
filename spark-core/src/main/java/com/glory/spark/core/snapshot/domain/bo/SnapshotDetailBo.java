/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.domain.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.data.jpa.domain.bo.DomainBo;
import com.glory.data.jpa.domain.bo.DomainBoWithDynamicFields;
import com.glory.spark.core.domain.type.RetryStrategy;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.domain.type.TaskStatus;
import jakarta.persistence.Convert;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/3
 * @descprition :
 *
 */

public class SnapshotDetailBo extends DomainBoWithDynamicFields {
    private Long detailId;
    private Long snapshotId;
    private Long relatedId;
    private String sparkCode;
    private String type;
    private String taskCode;
	private String tenant;
    private String traceId;
    @Convert(converter = SourceFrom.SourceFromConverter.class)
    private SourceFrom source;
    @Convert(converter = TaskStatus.TaskStatusConverter.class)
    private TaskStatus status;
    private String message;
    private Long targetId;
    @Convert(converter = RetryStrategy.RetryStrategyConverter.class)
    private RetryStrategy strategy;
    private int retryIndex=0;
    private LocalDateTime lastRetryTime;
    private transient SnapshotBo snapshot;
	{
		ignoredProperties.add("snapshot");
	}

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

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
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
        Optional.ofNullable(snapshot).ifPresent(s->s.updateStatus(status));
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

	@JsonIgnore
    public SnapshotBo getSnapshot() {
        return snapshot;
    }

    @JsonIgnore
    public void setSnapshot(SnapshotBo snapshot) {
        this.snapshot = snapshot;
    }

	@Override
	public Long getPrimaryId() {
		return getDetailId();
	}
}
