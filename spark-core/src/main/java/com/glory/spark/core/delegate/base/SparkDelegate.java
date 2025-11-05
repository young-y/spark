/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.base;


import com.glory.spark.core.context.SparkContext;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/10/29
 * @descprition :
 *
 */

public interface SparkDelegate<S> {

    S delegate(SparkContext<?> context);

    List<S> delegates(SparkContext<?> context);
}
