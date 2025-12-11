/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.exception.impl;


import com.glory.spark.core.component.exception.ExceptionCapture;
import com.glory.spark.core.exception.SparkException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.glory.spark.core.context.SparkConstant.EXCEPTION_PREFIX;
import static com.glory.spark.core.context.SparkConstant.E_PREFIX_STRATEGY;

/**
 * @author : YY
 * @date : 2025/11/5
 * @descprition :
 *
 */
@Component
@Order(1001)
@ConditionalOnProperty(prefix = EXCEPTION_PREFIX,name = E_PREFIX_STRATEGY,havingValue = "panic",matchIfMissing = false)
public class PanicExceptionCapture implements ExceptionCapture {

    @Override
    public void handle(SparkException exception) {
        throw exception;
    }
}
