/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.controller;


import com.glory.spark.core.component.base.SparkTypeSupport;
import com.glory.spark.core.component.base.TenantSupport;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/10/29
 * @descprition :
 *
 */

public interface TypeController extends SparkTypeSupport {

    <T,E>SparkResult<E> process(SparkContext<T> context);

    <T>void asyncProcess(SparkContext<T> context);

    @Component
    @Order(Integer.MAX_VALUE)
    class Impl implements TypeController {

        @Override
        public <T, E> SparkResult<E> process(SparkContext<T> context) {
            return null;
        }

        @Override
        public <T> void asyncProcess(SparkContext<T> context) {

        }
    }
}
