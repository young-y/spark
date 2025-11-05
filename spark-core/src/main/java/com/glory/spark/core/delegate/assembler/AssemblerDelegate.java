/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.assembler;


import com.glory.spark.core.component.assembler.SparkAssembler;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.base.AbstractDelegate;
import com.glory.spark.core.domain.SparkResult;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/10/29
 * @descprition :
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
@Component
public class AssemblerDelegate extends AbstractDelegate<SparkAssembler> {

    public <E>SparkResult<E> assemble(SparkContext context ){
        return delegate(context).assemble(context);
    }
}
