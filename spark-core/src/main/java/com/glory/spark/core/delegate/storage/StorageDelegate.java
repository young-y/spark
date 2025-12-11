/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.storage;


import com.glory.spark.core.component.storage.StorageHandler;
import com.glory.spark.core.delegate.base.AbstractDelegate;
import com.glory.spark.core.domain.SparkResult;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/11/6
 * @descprition :
 *
 */
@Component
public class StorageDelegate extends AbstractDelegate<StorageHandler> {

    public <E> void stored(SparkResult<E> result){
         delegate(result.getContext()).stored(result);
    }
}
