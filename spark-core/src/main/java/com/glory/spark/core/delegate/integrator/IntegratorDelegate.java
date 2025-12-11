/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.integrator;


import com.glory.spark.core.component.integrator.IntegratorRunner;
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
public class IntegratorDelegate extends AbstractDelegate<IntegratorRunner> {

    public <E>void run(SparkResult<E> result){
        delegates(result.getContext()).forEach(r->{
            if (result.isSuspend()){
                return;
            }
            r.run(result);
        });

    }
}
