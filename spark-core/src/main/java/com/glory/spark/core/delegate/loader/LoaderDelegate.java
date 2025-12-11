/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.loader;


import com.glory.spark.core.component.loader.TaskLoader;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.base.AbstractDelegate;
import com.glory.spark.core.domain.SparkTaskDesc;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/6
 * @descprition :
 *
 */
@Component
public class LoaderDelegate extends AbstractDelegate<TaskLoader> {

    public <T>List<SparkTaskDesc> load(SparkContext<T> context){
        return delegate(context).load(context);
    }
}
