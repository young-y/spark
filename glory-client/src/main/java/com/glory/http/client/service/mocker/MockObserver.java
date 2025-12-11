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

/** it is a mock function for target method. When invoke method ,
 * if MockObserver condition has true,to invoke will be trans to ClientMocker subclass
 * {@link ClientMockerAspect}
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MockObserver {
	String condition() default "";//env property,if true,mock active

	/**
	 * Under complex conditions ,Mock activated
	 * @return
	 */
	Class<? extends MockCondition> conditionClass() default MockCondition.DefaultCondition.class;
	boolean ifMissing() default false;//When env property mis,the default value is false;
	String mocker() default "clientMocker";//the bean name of ClientMOcker subclass
	Class<? extends  ClientMocker> mockerClass() default ClientMocker.DefaultClientMocker.class;
}
