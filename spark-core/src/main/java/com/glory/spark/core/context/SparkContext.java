/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.context;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.foundation.domain.PropertyDesc;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.domain.SparkTypeDesc;
import com.glory.spark.core.domain.type.ConditionMode;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.domain.type.RetryStrategy;
import com.glory.spark.core.domain.type.SourceFrom;
import jakarta.annotation.Nonnull;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

/**spark flow context
 * @author : YY
 * @date : 2025/10/28
 * @descprition :
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class SparkContext <T> implements PropertyDesc {

    private T context;//master object of flow context
    private Object refObj;//the second object
    private String sparkCode;//require,the only identify of triggering
    private String taskCode;//unique identification of specific task
    private String type;//task category
	private String serialId;
	private int conditionMode = SparkConstant.PARAMETER_MODE_SPARK;
    private SourceFrom source = SourceFrom.Online;//trigger source
    private RetryStrategy retryStrategy;//compensate category
    private LocalDateTime processDate;
    private SparkTypeDesc typeDesc;
    private SparkTaskDesc taskDesc;
	private final Map<String,SparkTaskDesc> taskMap = new HashMap<>();
    private final Map<String, Object> properties = new HashMap<>(8);
    private String exceptionStrategy ;
    private SnapshotBo snapshotBo;
    public T getContext() {
        return context;
    }

    public SparkContext<T> setContext(T context) {
        this.context = context;
        return this;
    }

    public <O> O getRefObj() {
        return (O) refObj;
    }

    public SparkContext<T> setRefObj(Object refObj) {
        this.refObj = refObj;
        return this;
    }

    public String getSparkCode() {
        return sparkCode;
    }

    public SparkContext<T> setSparkCode(String sparkCode) {
        this.sparkCode = sparkCode;
        return this;
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

    public SparkContext<T>  setType(String type) {
        this.type = type;
        return this;
    }

	public String getSerialId() {
		if (!StringUtils.hasLength(serialId)){
			serialId = UUID.randomUUID().toString();
		}
		return serialId;
	}

	public SparkContext<T> setSerialId(String serialId) {
		this.serialId = serialId;
		return this;
	}

	public SourceFrom getSource() {
        return source;
    }

    public SparkContext<T> setSource(SourceFrom source) {
        this.source = source;
        return this;
    }

    public RetryStrategy getRetryStrategy() {
        return retryStrategy;
    }

    public SparkContext<T> setRetryStrategy(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
        return this;
    }

    public LocalDateTime getProcessDate() {
        return processDate;
    }

    public SparkContext<T> setProcessDate(LocalDateTime processDate) {
        this.processDate = processDate;
        return this;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties.putAll(properties);
    }

    public void addProperty(@Nonnull String key,Object value){
        properties.put(key,value);
    }

    public <V> V getProperty(@Nonnull String key){
        return (V) properties.get(key);
    }

    public String getExceptionStrategy() {
        return exceptionStrategy;
    }

    public SparkContext<T> setExceptionStrategy(String exceptionStrategy) {
        this.exceptionStrategy = exceptionStrategy;
        return this;
    }

    public SparkTypeDesc getTypeDesc() {
        return typeDesc;
    }

    public SparkContext<T> setTypeDesc(SparkTypeDesc typeDesc) {
        this.typeDesc = typeDesc;
        return this;
    }

    public SparkTaskDesc getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(SparkTaskDesc taskDesc) {
		Optional.ofNullable(taskDesc).ifPresent(task->{
			Assert.isTrue(!taskMap.containsKey(task.identity()),task.identity()+" Task has been exist");
			taskMap.put(task.identity(),task);
			this.taskDesc = task;
		});
	}

    public SnapshotBo getSnapshotInfo() {
        return snapshotBo;
    }

	@JsonIgnore
	public void updateSnapshotStatus(){
		updateSnapshotStatus(TaskStatus.Success,null);
	}
	@JsonIgnore
	public void updateSnapshotStatus(TaskStatus status,String message){
		Optional.ofNullable(snapshotBo).ifPresent(bo->{
			bo.setStatus(status);
			bo.setMessage(message);
		});
	}

    public void setSnapshotInfo(SnapshotBo snapshotBo) {
        this.snapshotBo = snapshotBo;
    }

	public void addConditionMode(ConditionMode mode){
		this.conditionMode |= mode.getCode();
	}

	public boolean isConditionMode(ConditionMode mode){
		return mode.getCode() ==( this.conditionMode & mode.getCode());
	}

	public int getConditionMode() {
		return conditionMode;
	}

	public SparkContext<T> copy(){
        SparkContext<T> c = new SparkContext<>();
        BeanUtils.copyProperties(this,c);
        return c;
    }

    @Override
    @JsonIgnore
    public Object getValue(String key) {
        if (null != taskDesc && taskDesc.getProperties().containsKey(key)){
            return taskDesc.getValue(key);
        } else if (null != typeDesc && typeDesc.getProperties().containsKey(key)) {
            return typeDesc.getValue(key);
        }
        return properties.get(key);
    }

    public static SparkContext create(String sparkCode){
        SparkContext sparkContext = new SparkContext();
        sparkContext.setSparkCode(sparkCode);
        return sparkContext;
    }

    public static<T> SparkContext<T> create(String sparkCode,T context){
        SparkContext<T> sparkContext = new SparkContext<>();
        sparkContext.setContext(context);
        sparkContext.setSparkCode(sparkCode);
        return sparkContext;
    }

}
