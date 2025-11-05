/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.launcher.impl;


import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkResult;
import com.glory.spark.core.launcher.SparkLauncher;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */

public abstract class AbstractLauncher implements SparkLauncher {

    protected Executor executor;

    @Override
    public <E, T> SparkResult<E> transmit(@Nonnull String sparkCode, T context) {
        SparkContext<T> sparkContext = new SparkContext<>();
        sparkContext.setSparkCode(sparkCode)
                .setContext(context);
        return transmit(sparkContext);
    }

    @Override
    public <T> void asyncTransmit(SparkContext<T> context) {
        CompletableFuture.runAsync(()->transmit(context),executor);
    }

    @Override
    public <T> void asyncTransmit(String sparkCode, T context) {
        CompletableFuture.runAsync(()->transmit(sparkCode,context),executor);
    }

    @Resource(name = "sparkExecutor")
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }


}
