/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.exception;


import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.exception.ExceptionDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author : YY
 * @date : 2025/11/5
 * @descprition :
 *
 */
@Component
public class ExceptionManager {

    private ExceptionDelegate delegate;

    public void handle(SparkException e){
        delegate.handle(e);
    }

    public <T> void handle(SparkContext<T> context,Exception exception){
        if (exception instanceof SparkException){
           if (Objects.isNull(((SparkException) exception).getContext())){
               ((SparkException) exception).setContext(context);
           }
           handle((SparkException) exception);
        }else {
            handle(new SparkException(exception).setContext(context));
        }
    }

    @Autowired
    public void setDelegate(ExceptionDelegate delegate) {
        this.delegate = delegate;
    }
}
