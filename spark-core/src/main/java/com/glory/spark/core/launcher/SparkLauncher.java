/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.launcher;


import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkResult;
import jakarta.annotation.Nonnull;

/**
 * @author : YY
 * @date : 2025/10/29
 * @descprition :
 *
 */

public interface SparkLauncher {

    <E,T>SparkResult<E> transmit(SparkContext<T> context);

    <E,T>SparkResult<E> transmit(@Nonnull String sparkCode, T context);

    <T>void asyncTransmit(SparkContext<T> context);

    <T>void asyncTransmit(String sparkCode,T context);
}
