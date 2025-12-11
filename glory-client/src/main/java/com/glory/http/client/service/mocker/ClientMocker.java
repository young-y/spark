/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.mocker;


import org.springframework.beans.BeanUtils;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */
@FunctionalInterface
public interface ClientMocker {

	<T> T mock(Object[] argus,Class<T> responseType);

	class DefaultClientMocker implements ClientMocker {

		/**
		 * @param responseType
		 * @param <T>
		 * @return
		 */
		@Override
		public <T> T mock(Object[] argus,Class<T> responseType) {
			return BeanUtils.instantiateClass(responseType);
		}
	}
}
