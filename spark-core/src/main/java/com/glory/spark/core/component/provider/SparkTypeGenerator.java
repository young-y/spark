/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.provider;


import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTypeDesc;

import java.util.List; /**
 * @author : YY
 * @date : 2025/11/8
 * @descprition :
 *
 */
@FunctionalInterface
public interface SparkTypeGenerator {

    @SuppressWarnings("rawtypes")
	List<SparkTypeDesc> generate(SparkContext context);
}
