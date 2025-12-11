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
import com.glory.data.jpa.domain.type.ResultStatus;
import jakarta.annotation.Nonnull;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/10/29
 * @descprition :
 *
 */
@SuppressWarnings("rawtypes")
public class SparkResult<E> {

    private List<E> elements;
    private ResultStatus status= ResultStatus.Success;
    private List<String> messages;
	@JsonIgnore
    private transient SparkContext context;
    private transient boolean suspend = false;

    public List<E> getElements() {
        return elements;
    }

    public void setElements(List<E> elements) {
		Optional.ofNullable(elements).ifPresent(es->{
			if (null ==this.elements){
				this.elements = es;
			}else {
				this.elements.addAll(es);
			}
		});
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

    public SparkResult<E> setStatus(ResultStatus status) {
        this.status = status;
        return this;
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
    public SparkContext getContext() {
        return context;
    }

    @JsonIgnore
    public SparkResult<E> setContext(SparkContext context) {
        this.context = context;
        return this;
    }

    @JsonIgnore
    public boolean isSuspend() {
        return suspend;
    }

    @JsonIgnore
    public void setSuspend(boolean suspend) {
        this.suspend = suspend;
    }

    public void updateResult(SparkResult<E> result){
        Optional.ofNullable(result).ifPresent(r->{
            if (ResultStatus.Success ==r.getStatus()){
                setElements(r.getElements());
            }else {
                if (getStatus().getValue() < r.getStatus().getValue()){
                    setStatus(r.getStatus());
                }
                setMessages(r.getMessages());
            }
        });
    }

    @JsonIgnore
    public static <E,T> SparkResult<E> Success(SparkContext<T> context){
        return new SparkResult<E>().setContext(context);
    }

    @JsonIgnore
    public static <E,T> SparkResult<E> Fail(SparkContext<T> context){
        return new SparkResult<E>().setContext(context).setStatus(ResultStatus.Fail);
    }
}
