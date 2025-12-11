/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.converter;


import com.glory.foundation.formater.FormatHelper;
import jakarta.persistence.AttributeConverter;

import java.util.Map;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */

public class TypedMapConverter implements AttributeConverter<Map<String,Object>, String> {


    /**
     * @param map
     * @return
     */
    @Override
    public String convertToDatabaseColumn(Map map) {
        return FormatHelper.toJson(map);
    }

    /**
     * @param str
     * @return
     */
    @Override
    public Map<String,Object> convertToEntityAttribute(String str) {
        return FormatHelper.fromJsonAsMap(str, String.class,Object.class);
    }
}
