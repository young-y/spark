/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.domain.snapshot;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class SnapshotInfo {
    private long snapshotId;
    private String sparkCode;
    private String serialId;
    private String appName;
    private String contextPath;
    private LocalDateTime processDate;
    @Convert(converter = SourceFrom.SourceFromConverter.class)
    private SourceFrom source = SourceFrom.Online;
    @Convert(converter = TaskStatus.TaskStatusConverter.class)
    private TaskStatus status = TaskStatus.Init;
    private Object context;
    private String contextType;
    private Object refObject;
    private String refType;
    private String operator;
    private Map<String,Object> properties;
    private List<SnapshotDetail> details;

    public long getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(long snapshotId) {
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

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public Object getRefObject() {
        return refObject;
    }

    public void setRefObject(Object refObject) {
        this.refObject = refObject;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public List<SnapshotDetail> getDetails() {
        return details;
    }

    public void setDetails(List<SnapshotDetail> details) {
        this.details = details;
    }

    @JsonIgnore
    public void addDetail(@Nonnull SnapshotDetail detail){
        if (null == this.details){
            this.details = new ArrayList<>();
        }
        this.details.add(detail);
    }

    @JsonIgnore
    public SnapshotDetail findDetail(String type,String taskCode){
        Optional.ofNullable(this.details)
                .orElseThrow(()->new SparkException("The detail["+sparkCode+"] is empty."));
        return this.details.stream().
                filter(d->d.getTaskCode().equals(taskCode)&& d.getType().equals(taskCode))
                .findFirst().orElseThrow(()-> new SparkException(String.format("can't find detail by[%s-%s]",type,taskCode)));
    }


}
