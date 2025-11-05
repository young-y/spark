/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.exception;

import com.glory.spark.core.context.SparkContext;

public class SparkException extends RuntimeException {
    private SparkContext<?> context;
    private boolean suspend = false;
    public SparkException(String message) {
        super(message);
    }

    public SparkException(Throwable cause){
        super(cause);
    }

    public SparkException(String message,Throwable cause){
        super(message, cause);
    }
    public SparkContext<?> getContext() {
        return context;
    }

    public SparkException setContext(SparkContext<?> context) {
        this.context = context;
        return this;
    }

    public boolean isSuspend() {
        return suspend;
    }

    public SparkException setSuspend(boolean suspend) {
        this.suspend = suspend;
        return this;
    }
}
