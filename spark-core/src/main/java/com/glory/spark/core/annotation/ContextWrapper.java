/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.annotation;


/**
 * @author : YY
 * @date : 2025/11/26
 * @descprition :
 *
 */
@FunctionalInterface
public interface ContextWrapper {
	Object pack(Object[] argus);

	class Impl implements ContextWrapper{

		/**
		 * @param argus
		 * @return
		 */
		@Override
		public  Object pack(Object[] argus) {
			if (null != argus && argus.length >0){
				return argus[0];
			}
			return null;
		}
	}
}
