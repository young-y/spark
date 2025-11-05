/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.exception.impl;


import com.glory.spark.core.component.exception.ExceptionHandler;
import com.glory.spark.core.exception.SparkException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/11/5
 * @descprition :
 *
 */
@Component
@Order(1001)
@ConditionalOnProperty(prefix = "spark.exception",name = "strategy",havingValue = "panic",matchIfMissing = false)
public class PanicExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(SparkException exception) {
        throw exception;
    }
}
