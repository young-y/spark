/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.domain;


import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author : YY
 * @date : 2025/10/28
 * @descprition :
 *
 */

@SuppressWarnings({"rawtypes"})
public  interface  PersistableEnum <V>{

    static <T extends PersistableEnum, V> T valueOf(V value, Class<T> clazz) {
        T constant = resolve(value, clazz);
        if (constant == null) {
            throw new IllegalArgumentException("No matching constant for " + clazz.getCanonicalName() + ".[" + value + "]");
        }
        return constant;

    }

    static <T extends PersistableEnum, V> T resolve(V value, Class<T> clazz) {
        T[] values = clazz.getEnumConstants();
        for (T e : values) {
            if (e.value().equals(value)) {
                return e;
            }
        }
        return null;
    }

    @JsonValue
    V value();


}
