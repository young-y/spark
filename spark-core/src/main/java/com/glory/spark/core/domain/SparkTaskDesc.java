/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.foundation.domain.PropertyDesc;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import com.glory.spark.core.utils.DateUtils;
import jakarta.annotation.Nonnull;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class SparkTaskDesc implements PropertyDesc {
    private String sparkCode;
    private String taskCode;
    private String type;
    private String taskName;
    private String taskDescription;
    private boolean sync = true;
    private boolean enabled = true;
    private String exceptionStrategy;
    private LocalDateTime startEffectiveTime;
    private LocalDateTime endEffectiveTime;
    private final Map<String,Object> properties = new HashMap<>(16);
    private transient SparkContext context;
    private transient SnapshotDetailBo detail;
    public String getSparkCode() {
        return sparkCode;
    }

    public void setSparkCode(String sparkCode) {
        this.sparkCode = sparkCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getExceptionStrategy() {
        return exceptionStrategy;
    }

    public void setExceptionStrategy(String exceptionStrategy) {
        this.exceptionStrategy = exceptionStrategy;
    }

    public LocalDateTime getStartEffectiveTime() {
        return startEffectiveTime;
    }

    public void setStartEffectiveTime(LocalDateTime startEffectiveTime) {
        if (null !=this.endEffectiveTime && null != startEffectiveTime){
            Assert.isTrue(startEffectiveTime.isAfter(endEffectiveTime),"StartTime greater than EndTime.");
        }
        this.startEffectiveTime = startEffectiveTime;
    }

    public LocalDateTime getEndEffectiveTime() {
        return endEffectiveTime;
    }

    public void setEndEffectiveTime(LocalDateTime endEffectiveTime) {
        if (null !=this.startEffectiveTime && null != endEffectiveTime){
            Assert.isTrue(endEffectiveTime.isBefore(this.startEffectiveTime),"EndTimme less than StartTime.");
        }
        this.endEffectiveTime = endEffectiveTime;
    }

    @JsonIgnore
    public boolean isEffective(LocalDateTime dateTime){
        return isEnabled() && DateUtils.isEffective(startEffectiveTime,endEffectiveTime,dateTime);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        Optional.ofNullable(properties).ifPresent(this.properties::putAll);
    }

    @JsonIgnore
    public void setContext(SparkContext context) {
        this.context = context;
    }

    @JsonIgnore
    public SparkContext getContext() {
        return context;
    }

    @JsonIgnore
    public SnapshotDetailBo getDetail() {
        return detail;
    }

    @JsonIgnore
    public void setDetail(SnapshotDetailBo detail) {
        this.detail = detail;
    }

	@JsonIgnore
	public void updateDetailStatus(TaskStatus status,String message){
		Optional.ofNullable(this.detail).ifPresent(d->{
			if (TaskStatus.Compensate != d.getStatus()){
				d.setStatus(status);
				d.setMessage(message);
			}
		});
	}
    @JsonIgnore
    public  SparkTaskDesc addProperty(@Nonnull String key, @Nonnull Object value){
        properties.put(key, value);
        return this;
    }

    @Override
    @JsonIgnore
    public Object getValue(String key) {
        return properties.get(key);
    }

    @JsonIgnore
    public String identity(){
        return String.format("Task[%s:%s:%s]",sparkCode,type,taskCode);
    }

    @JsonIgnore
    public<T> SparkContext<T> copy(){
        Assert.isTrue(null != context,"SparkContext is null.");
        Assert.hasLength(taskCode,"TaskCode is empty.");
        context.setTaskCode(taskCode);
        SparkContext<T> sparkContext = context.copy();
        Optional.ofNullable(exceptionStrategy).ifPresent(sparkContext::setExceptionStrategy);
        sparkContext.setTaskDesc(this);
		this.setContext(sparkContext);
        return sparkContext;
    }

    public static SparkTaskDesc create(@Nonnull SparkTypeDesc typeDesc){
        SparkTaskDesc taskDesc = new SparkTaskDesc();
        taskDesc.setSparkCode(typeDesc.getSparkCode());
        taskDesc.setType(typeDesc.getType());
        taskDesc.setProperties(typeDesc.getProperties());
        return taskDesc;
    }
}
