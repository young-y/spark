/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.mocker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MockObserver {
	String condition() default "";
	Class<? extends MockCondition> conditionClass() default MockCondition.DefaultCondition.class;
	boolean ifMissing() default false;
	String mocker() default "clientMocker";
	Class<? extends  ClientMocker> mockerClass() default ClientMocker.DefaultClientMocker.class;
}
