/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : YY
 * @date : 2025/11/26
 * @descprition :
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Spark {
	String sparkCode();
	String type() default "";
	String taskCode() default "";
	String condition() default "";
	Class<? extends SparkCondition> conditionClass() default SparkCondition.Impl.class;
	boolean ifMissing() default true;
	boolean sync() default true;
	int argumentIndex() default 0;
	Class<? extends ContextWrapper> contextWrapper() default ContextWrapper.Impl.class;
	SparkOccasion occasion() default SparkOccasion.After;
	boolean catchException() default false;
 }
