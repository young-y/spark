/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.controller.impl;


import com.glory.spark.core.component.controller.TypeController;
import com.glory.spark.core.context.SparkContext;
import jakarta.annotation.Resource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */

public abstract class AbstractTypeController implements TypeController {
    protected Executor executor;
    @Override
    public <T> void asyncProcess(SparkContext<T> context) {
        CompletableFuture.runAsync(()->process(context),executor);
    }

    @Resource(name="sparkExecutor")
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}
