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
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.exception.SparkException;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Convert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/3
 * @descprition :
 *
 */

public class SnapshotBo extends DomainBoWithDynamicFields {
    private Long snapshotId;
    private String sparkCode;
    private String serialId;
	private int conditionMode;
    private String appName;
    private String contextPath;
	private String tenant;
    private LocalDateTime processDate;
    @Convert(converter = SourceFrom.SourceFromConverter.class)
    private SourceFrom source = SourceFrom.Online;
    @Convert(converter = TaskStatus.TaskStatusConverter.class)
    private TaskStatus status = TaskStatus.Init;
	private String message;
    private String context;
    private String contextType;
    private String refObject;
    private String refType;
//    private Map<String,Object> properties;
    private List<SnapshotDetailBo> details;
	{
		ignoredProperties.add("details");
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

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

	public int getConditionMode() {
		return conditionMode;
	}

	public void setConditionMode(int conditionMode) {
		this.conditionMode = conditionMode;
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

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public LocalDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDateTime processDate) {
        this.processDate = processDate;
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

	public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getRefObject() {
        return refObject;
    }

    public void setRefObject(String refObject) {
        this.refObject = refObject;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

	public List<SnapshotDetailBo> getDetails() {
        return details;
    }

    public void setDetails(List<SnapshotDetailBo> details) {
        this.details = details;
    }

    @JsonIgnore
    public void addDetail(@Nonnull SnapshotDetailBo detail){
        if (null == this.details){
            this.details = new ArrayList<>();
        }
        detail.setSnapshot(this);
        this.details.add(detail);
    }

	@JsonIgnore
    public void updateStatus(TaskStatus status){
        if (null != status ){
            if (this.status == TaskStatus.Init || this.status.getValue()> status.getValue()){
                setStatus(status);
            }
        }
    }

    @JsonIgnore
    public SnapshotDetailBo findDetail(String type,String taskCode){
        Optional.ofNullable(this.details)
                .orElseThrow(()->new SparkException("The detail["+sparkCode+"] is empty."));
        return this.details.stream().
                filter(d->d.getTaskCode().equals(taskCode)&& d.getType().equals(type))
                .findFirst().orElseThrow(()-> new SparkException(String.format("can't find detail by[%s-%s]",type,taskCode)));
    }

	@Override
	public Long getPrimaryId() {
		return getSnapshotId();
	}
}
