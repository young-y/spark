/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.launcher.impl;


import com.glory.http.client.service.mocker.MockObserver;
import com.glory.spark.core.component.provider.SparkTypeGenerator;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.controller.ControllerDelegate;
import com.glory.spark.core.delegate.filter.TypeFilterDelegate;
import com.glory.spark.core.delegate.provider.ProviderDelegate;
import com.glory.spark.core.domain.SparkResult;
import com.glory.spark.core.domain.SparkTypeDesc;
import com.glory.data.jpa.domain.type.ResultStatus;
import com.glory.spark.core.domain.type.ConditionMode;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.exception.ExceptionManager;
import com.glory.spark.core.exception.SparkException;
import com.glory.spark.core.snapshot.listener.SnapshotListener;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    private List<SnapshotListener> snapshotListeners;
    private ExceptionManager exceptionManager;
    private TypeFilterDelegate typeFilterDelegate;
	private SparkTypeGenerator typeGenerator;
    @Override
	@MockObserver(condition = "spark.launcher.disabled")//if true,closed the spark function
    public <E, T> SparkResult<E> transmit(@Nonnull SparkContext<T> context) {
        Assert.hasLength(context.getSparkCode(),"sparkCode is empty.");
        SparkResult<E> result = SparkResult.Success(context);
        try{
            List<SparkTypeDesc> types = loadSparkTypes(context); //providerDelegate.provide(context);//find spark type controller by sparkCode
            if (CollectionUtils.isEmpty(types)){//return if can't find sparkType
                logger.debug("Spark[{}] provide types is empty.",context.getSparkCode());
                return result;
            }
            logger.debug("Spark[{}] provide types size:{}.",context.getSparkCode(),types.size());
            snapshotListener().capture(context);//log snapshot
            for(SparkTypeDesc type: types){//foreach all sparkType
                SparkContext<T> typeContext = type.copy();
                if (typeFilterDelegate.filter(typeContext)){//filter effective and enabled type
                    if (type.isSync()){// sync process
                        SparkResult<E> sr =controllerDelegate.process(typeContext);
                        result.updateResult(sr);
                    }else {//async process,ignored result
                        controllerDelegate.asyncProcess(context);
                    }
                }
            }
			context.updateSnapshotStatus();
        }catch (Exception e){
            result.setStatus(ResultStatus.Fail);
            result.addMessage(e.getMessage());
			context.updateSnapshotStatus(TaskStatus.Fail,e.getMessage());
            logger.warn("Spark[{}] transmit exception :",context.getSparkCode(),e);
            exceptionManager.handle(new SparkException(e).setContext(context));//handle exception
        }finally {
			snapshotListener().update(context.getSnapshotInfo());
		}
        return result;
    }

	private <T>List<SparkTypeDesc> loadSparkTypes(SparkContext<T> context){
		if (StringUtils.hasLength(context.getTaskCode())){
			context.addConditionMode(ConditionMode.TaskCode);
		}
		if (!StringUtils.hasLength(context.getType())){
			return providerDelegate.provide(context);
		}
		context.addConditionMode(ConditionMode.Type);
		return typeGenerator.generate(context);
	}

	private SnapshotListener snapshotListener(){
		return snapshotListeners.getFirst();
	}

	@Autowired
	public void setSnapshotListeners(List<SnapshotListener> snapshotListeners) {
		this.snapshotListeners = snapshotListeners;
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
    public void setExceptionManager(ExceptionManager exceptionManager) {
        this.exceptionManager = exceptionManager;
    }

    @Autowired
    public void setTypeFilterDelegate(TypeFilterDelegate typeFilterDelegate) {
        this.typeFilterDelegate = typeFilterDelegate;
    }

	@Autowired
	public void setTypeGenerator(SparkTypeGenerator typeGenerator) {
		this.typeGenerator = typeGenerator;
	}
}
