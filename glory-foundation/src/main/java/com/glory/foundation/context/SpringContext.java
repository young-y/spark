/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.context;


import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author : YY
 * @date : 2025/12/18
 * @descprition :
 *
 */

public class SpringContext {
	private static ApplicationContext context;
	private static Environment environment;


	public static void setEnvironment(Environment environment) {
		SpringContext.environment = environment;
	}

	public static void setContext(ApplicationContext context) {
		SpringContext.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static Environment getEnvironment() {
		return environment;
	}

	public static <T> T getBean(String name, Class<T> beanType){
		return context.getBean(name,beanType);
	}
}
