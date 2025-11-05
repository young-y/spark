/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.exception;


import com.glory.spark.core.component.base.TenantSupport;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.exception.SparkException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/10/28
 * @descprition :
 *
 */

public interface ExceptionHandler extends TenantSupport {

    void handle(SparkException exception);

    default List<String> supportStrategies(){
        return List.of();
    }

    default boolean match(SparkContext<?> context){
        return  match(supportTenants(),null)
                && match(supportStrategies(), context.getExceptionStrategy());
    }

    @Component
    @Order(Integer.MAX_VALUE)
    class Impl implements ExceptionHandler{

        @Override
        public void handle(SparkException exception) {

        }
    }

}
