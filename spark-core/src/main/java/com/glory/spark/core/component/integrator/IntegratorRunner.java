/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.integrator;


import com.glory.spark.core.component.base.TaskSupport;
import com.glory.spark.core.domain.SparkResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/11/6
 * @descprition :
 *
 */

public interface IntegratorRunner<E> extends TaskSupport {

    void run(SparkResult<E> result);

    @Component
    @Order(Integer.MAX_VALUE)
    class Impl<E> implements IntegratorRunner<E> {
        @Override
        public  void run(SparkResult<E> result) {

        }
    }
}
