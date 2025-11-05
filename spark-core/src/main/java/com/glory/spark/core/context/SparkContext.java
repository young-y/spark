/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.context;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.spark.core.domain.PropertyDesc;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.domain.SparkTypeDesc;
import com.glory.spark.core.domain.snapshot.SnapshotInfo;
import com.glory.spark.core.domain.type.SourceFrom;
import jakarta.annotation.Nonnull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/10/28
 * @descprition :
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class SparkContext <T> implements PropertyDesc {

    private T context;
    private Object refObj;
    private String sparkCode;
    private String taskCode;
    private String type;
    private SourceFrom source = SourceFrom.Online;
    private LocalDateTime processDate;
    private SparkTypeDesc typeDesc;
    private SparkTaskDesc taskDesc;
    private final Map<String, Object> properties = new HashMap<>(8);
    private String exceptionStrategy ;
    private SnapshotInfo snapshotInfo;
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

    public void setType(String type) {
        this.type = type;
    }

    public SourceFrom getSource() {
        return source;
    }

    public SparkContext<T> setSource(SourceFrom source) {
        this.source = source;
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
        this.taskDesc = taskDesc;
    }

    public SnapshotInfo getSnapshotInfo() {
        return snapshotInfo;
    }

    public void setSnapshotInfo(SnapshotInfo snapshotInfo) {
        this.snapshotInfo = snapshotInfo;
    }

    public SparkContext<T> copy(){
        SparkContext<T> c = new SparkContext<>();
        BeanUtils.copyProperties(this,c);
        return c;
    }

    @Override
    @JsonIgnore
    public Object getValue(String key) {
        return properties;
    }

    public static SparkContext create(String sparkCode){
        SparkContext sparkContext = new SparkContext();
        sparkContext.setContext(sparkCode);
        return sparkContext;
    }

    public static<T> SparkContext<T> create(String sparkCode,T context){
        SparkContext<T> sparkContext = new SparkContext<>();
        sparkContext.setContext(context);
        sparkContext.setSparkCode(sparkCode);
        return sparkContext;
    }

}
