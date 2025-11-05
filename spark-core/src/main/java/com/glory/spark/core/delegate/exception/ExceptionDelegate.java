/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.exception;


import com.glory.spark.core.component.exception.ExceptionHandler;
import com.glory.spark.core.delegate.base.AbstractDelegate;
import com.glory.spark.core.exception.SparkException;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/11/5
 * @descprition :
 *
 */
@Component
public class ExceptionDelegate extends AbstractDelegate<ExceptionHandler> {

    public void handle(SparkException e){
        delegates(e.getContext()).forEach(h->{
            if (e.isSuspend()){
                return;
            }
            handle(e);
        });
    }
}
