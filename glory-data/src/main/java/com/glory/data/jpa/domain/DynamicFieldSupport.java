/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain;


import jakarta.annotation.Nonnull;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */

public interface DynamicFieldSupport {

    /**
     *
     * @param key
     * @param value
     */
    void setFieldValue(@Nonnull String key, Object value);

    /**
     * @param key
     * @return
     */
    Object getFieldValue(@Nonnull String key);
}
