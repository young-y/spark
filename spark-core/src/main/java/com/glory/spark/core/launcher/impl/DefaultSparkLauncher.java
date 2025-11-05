/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.launcher.impl;


import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.controller.ControllerDelegate;
import com.glory.spark.core.delegate.provider.ProviderDelegate;
import com.glory.spark.core.domain.SparkResult;
import com.glory.spark.core.domain.SparkTypeDesc;
import com.glory.spark.core.domain.type.ResultStatus;
import com.glory.spark.core.exception.ExceptionManager;
import com.glory.spark.core.exception.SparkException;
import com.glory.spark.core.snapshot.SnapshotListener;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/10/29
 * @descprition :
 *
 */

public class DefaultSparkLauncher extends AbstractLauncher{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ProviderDelegate providerDelegate;
    private ControllerDelegate controllerDelegate;
    private SnapshotListener snapshotListener;
    private ExceptionManager exceptionManager;
    @Override
    public <E, T> SparkResult<E> transmit(@Nonnull SparkContext<T> context) {
        Assert.hasLength(context.getSparkCode(),"sparkCode is empty.");
        SparkResult<E> result = SparkResult.Success();
        try{
            List<SparkTypeDesc> typeControls = providerDelegate.provide(context);
            if (CollectionUtils.isEmpty(typeControls)){
                logger.debug("Spark[{}] provide types is empty.",context.getSparkCode());
                return result;
            }
            List<SparkTypeDesc> types = typeControls.stream().filter(t -> t.isEffective(context.getProcessDate())).toList();
            if (!CollectionUtils.isEmpty(types)){
                logger.debug("Spark[{}] provide effective types size:{}.",context.getSparkCode(),types.size());
                snapshotListener.capture(context);
                for(SparkTypeDesc type: types){
                    if (type.isEffective(null)){
                        SparkContext<T> copy = type.copy();
                        SparkResult<E> sr =controllerDelegate.process(copy);
                        if (ResultStatus.Success ==sr.getStatus()){
                            result.setElements(sr.getElements());
                        }else {
                            if (sr.getStatus().getValue() < result.getStatus().getValue()){
                                result.setStatus(sr.getStatus());
                                result.setMessages(sr.getMessages());
                            }
                        }
                    }
                }
                snapshotListener.updateSnapshot(context.getSnapshotInfo(), null);
            }else {
                if (logger.isDebugEnabled()){
                    logger.debug("Spark[{}] provide type is empty.",context.getSparkCode());
                }
            }
        }catch (Exception e){
            exceptionManager.handle(new SparkException(e).setContext(context));
            result.setStatus(ResultStatus.Fail);
            result.addMessage(e.getMessage());
            snapshotListener.updateSnapshot(context.getSnapshotInfo(),e);
            logger.warn("Spark[{}] transmit exception :",context.getSparkCode(),e);
        }
        return result;
    }


    @Autowired
    public void setProviderDelegate(ProviderDelegate providerDelegate) {
        this.providerDelegate = providerDelegate;
    }

    @Autowired
    public void setControllerDelegate(ControllerDelegate controllerDelegate) {
        this.controllerDelegate = controllerDelegate;
    }

    @Autowired
    public void setSnapshotListener(SnapshotListener snapshotListener) {
        this.snapshotListener = snapshotListener;
    }

    @Autowired
    public void setExceptionManager(ExceptionManager exceptionManager) {
        this.exceptionManager = exceptionManager;
    }
}
