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

import java.util.Set;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */

public class TypedSetStringConverter implements AttributeConverter<Set<String>, String> {

    /**
     * @param attributes
     * @return
     */
    @Override
    public String convertToDatabaseColumn(Set attributes) {
        return FormatHelper.toJson(attributes);
    }

    /**
     * @param dbStr
     * @return
     */
    @Override
    public Set<String> convertToEntityAttribute(String dbStr) {
        return FormatHelper.fromJsonAsSet(dbStr, String.class);
    }
}
