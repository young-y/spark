/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.provider;


import com.glory.spark.core.component.base.SparkCodeSupport;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTypeDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */

public interface TypeProvider extends SparkCodeSupport {

    <T> List<SparkTypeDesc> provide(SparkContext<T> context);

    @Component
    @Order(Integer.MAX_VALUE)
    class Impl implements TypeProvider {
        private final Logger logger = LoggerFactory.getLogger(getClass());
        @Override
        public <T> List<SparkTypeDesc> provide(SparkContext<T> context) {
            if (logger.isTraceEnabled()){
                logger.trace("ControlProvider default.");
            }
            return List.of();
        }
    }
}
