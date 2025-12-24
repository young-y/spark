/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.type.converter;


import com.glory.foundation.formater.FormatHelper;
import com.glory.foundation.type.WithTypeObject;
import jakarta.persistence.AttributeConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */

public class WithTypedSetConverter implements AttributeConverter<Set<Object>, String> {

    /**
     * @param attributes
     * @return
     */
    @Override
    public String convertToDatabaseColumn(Set<Object> attributes) {
		if (!CollectionUtils.isEmpty(attributes)){
			Set<WithTypeObject> typeObjects = attributes.stream().map(WithTypeObject::create).collect(Collectors.toSet());
			return FormatHelper.toJson(typeObjects);
		}
        return null;
    }

    /**
     * @param dbStr
     * @return
     */
	@Override
    public Set<Object> convertToEntityAttribute(String dbStr) {
		if (StringUtils.hasLength(dbStr)){
			Set<WithTypeObject> typeObjects = FormatHelper.fromJsonAsSet(dbStr, WithTypeObject.class);
			if (!CollectionUtils.isEmpty(typeObjects)){
				return typeObjects.stream().map(WithTypeObject::getRawTypeObject).collect(Collectors.toSet());
			}
		}
        return null;
    }
}
