/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.annotation;


import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.launcher.SparkLauncher;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author : YY
 * @date : 2025/11/26
 * @descprition :
 *
 */
@Aspect
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class SparkAspect {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private Environment environment;
	@Autowired
	private SparkLauncher launcher;

	@Around("@annotation(com.glory.spark.core.annotation.Spark)")
	public Object transmit(ProceedingJoinPoint point) throws Throwable{
		Method method = ((MethodSignature) point.getSignature()).getMethod();
		Object[] argus = point.getArgs();
		Spark spark = method.getAnnotation(Spark.class);
		Assert.hasLength(spark.sparkCode(),"SparkCode is empty.");
		if (conditionMatch(spark,argus,method)){
			SparkContext context = SparkContext.create(spark.sparkCode());
			if (StringUtils.hasLength(spark.type())){
				context.setType(spark.type());
			}
			if (StringUtils.hasLength(spark.taskCode())){
				context.setTaskCode(spark.taskCode());
			}
			initContext(context,spark,argus);
			SparkOccasion occasion = spark.occasion();
			logger.debug(">> Spark[{}#{}#{}#{}] transmit.",method.getDeclaringClass().getSimpleName(),method.getName(),occasion,spark.sparkCode());
			try {
				if (SparkOccasion.Before == occasion){
					doTransmit(context,spark);
				}
				Object result = point.proceed();
				if (SparkOccasion.After == occasion){
					doTransmit(context,spark);
				}
				return result;
			}catch (Throwable t){
				if (SparkOccasion.Throwing == occasion){
					doTransmit(context,spark);
				}
				if (!spark.catchException()){
					throw t;
				}else {
					logger.warn(">> Spark[{}#{}] transmit catch exception:{}.",method.getName(),spark.sparkCode(),t.getMessage());
					return null;
				}
			}
		}
		logger.debug(">> Spark[{}#{}#{}] condition false.",method.getDeclaringClass(),method.getName(),spark.sparkCode());
		return point.proceed();
	}

	private void doTransmit(SparkContext context,Spark spark){
		if (spark.sync()){
			launcher.transmit(context);
		}else {
			launcher.asyncTransmit(context);
		}
	}

	private void initContext(SparkContext context,Spark spark,Object[] argus){
		if (ArrayUtils.isNotEmpty(argus)){
			if (spark.contextWrapper() != ContextWrapper.Impl.class){
				ContextWrapper wrapper = BeanUtils.instantiateClass(spark.contextWrapper());
				context.setContext(wrapper.pack(argus));
			}else {
				Assert.isTrue(spark.argumentIndex() < argus.length,"ArgumentIndex overstep the boundary");
				context.setContext(argus[spark.argumentIndex()]);
			}
		}
	}

	private boolean conditionMatch(Spark spark, Object[] argus,Method method) {
		if (SparkCondition.Impl.class != spark.conditionClass()) {
			SparkCondition condition = BeanUtils.instantiateClass(spark.conditionClass());
			return condition.match(method,argus);
		}
		if (StringUtils.hasLength(spark.condition())) {
			return environment.getProperty(spark.condition(), Boolean.class, spark.ifMissing());
		}
		return false;
	}
}
