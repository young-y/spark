/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.mocker;


import java.lang.reflect.Method;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */

public interface MockCondition {

	boolean match(Method method,Object[] argus);

	class DefaultCondition implements MockCondition {

		/**
		 * @param argus
		 * @return
		 */
		@Override
		public boolean match(Method method,Object[] argus) {
			return false;
		}
	}
}
