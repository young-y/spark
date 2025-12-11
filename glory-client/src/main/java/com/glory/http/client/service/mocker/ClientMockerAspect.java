/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.mocker;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */
@Aspect
@Component
public class ClientMockerAspect {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private Environment environment;
	@Autowired
	private ApplicationContext context;

	@Around("@annotation(com.glory.http.client.service.mocker.MockObserver)")
	public Object intercept(ProceedingJoinPoint point) throws Throwable {
		Method method = ((MethodSignature) point.getSignature()).getMethod();
		Object[] args = point.getArgs();
		MockObserver observer = method.getAnnotation(MockObserver.class);
		if (conditionMatch(observer, args,method)) {
			ClientMocker mocker = getClientMocker(observer);
			logger.debug(">> Mock class[{}] method[{}] mockerClass[{}]", method.getDeclaringClass(), method.getName(), mocker.getClass());
			return mocker.mock(args, method.getReturnType());
		}
		return point.proceed();
	}

	private ClientMocker getClientMocker(MockObserver observer) {
		Class<? extends ClientMocker> mockerClass = observer.mockerClass();
		ClientMocker clientMocker = null;
		if (mockerClass != ClientMocker.DefaultClientMocker.class) {
			clientMocker = BeanUtils.instantiateClass(mockerClass);
		} else {
			Assert.hasLength(observer.mocker(), "mocker name is null");
			clientMocker = context.getBean(observer.mocker(), ClientMocker.class);
		}
		Assert.notNull(clientMocker,"clientMocker not found.");
		return clientMocker;
	}

	private boolean conditionMatch(MockObserver mock, Object[] argus,Method method) {
		if (MockCondition.DefaultCondition.class != mock.conditionClass()) {
			MockCondition mockCondition = BeanUtils.instantiateClass(mock.conditionClass());
			return mockCondition.match(method,argus);
		}
		if (StringUtils.hasLength(mock.condition())) {
			return environment.getProperty(mock.condition(), Boolean.class, mock.ifMissing());
		}
		return false;
	}
}
