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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Order(1000)
@ConditionalOnProperty(prefix = EXCEPTION_PREFIX,name = E_PREFIX_STRATEGY ,havingValue = "try-catch",matchIfMissing = true)
public class TryCatchExceptionCapture implements ExceptionCapture {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void handle(SparkException exception) {
        logger.warn("Spark[{}] has exception:",exception.getContext().getSparkCode(),exception);
        exception.setSuspend(true);
    }
}
