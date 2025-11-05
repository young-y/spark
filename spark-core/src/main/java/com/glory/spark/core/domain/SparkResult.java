/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.type.ResultStatus;
import jakarta.annotation.Nonnull;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YY
 * @date : 2025/10/29
 * @descprition :
 *
 */
@SuppressWarnings("rawtypes")
public class SparkResult<E> {

    private ResultStatus status= ResultStatus.Success;
    private List<E> elements;
    private List<String> messages;

    @JsonIgnore
    private SparkContext sparkContext;

    public List<E> getElements() {
        return elements;
    }

    public void setElements(List<E> elements) {
        if (null ==this.elements){
            this.elements = elements;
        }else {
            this.elements.addAll(elements);
        }
    }

    @JsonIgnore
    public SparkResult<E> addElement(@Nonnull E element){
        if (null == elements){
            elements = new ArrayList<>();
        }
        elements.add(element);
        return this;
    }

    @JsonIgnore
    public E getFirstElement(){
        if (ResultStatus.Success == status && !CollectionUtils.isEmpty(this.elements)){
            return this.elements.getFirst();
        }
        return  null;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(@Nonnull List<String> messages) {
        if (null == this.messages){
            this.messages = messages;
        }else {
            this.messages.addAll(messages);
        }
    }

    @JsonIgnore
    public SparkResult<E> addMessage(@Nonnull String msg){
        if (null==this.messages){
            this.messages = new ArrayList<>();
        }
        this.messages.add(msg);
        return this;
    }

    @JsonIgnore
    public SparkContext getSparkContext() {
        return sparkContext;
    }

    @JsonIgnore
    public void setSparkContext(SparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }

    @JsonIgnore
    public static <T> SparkResult<T> Success(){
        return new SparkResult<>();
    }
}
