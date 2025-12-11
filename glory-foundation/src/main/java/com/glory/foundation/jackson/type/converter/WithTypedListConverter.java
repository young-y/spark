/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.jackson.type.converter;


import com.glory.foundation.formater.FormatHelper;
import com.glory.foundation.jackson.type.WithTypeObject;
import jakarta.persistence.AttributeConverter;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */

public class WithTypedListConverter implements AttributeConverter<List<Object>, String> {

	/**
	 * @param attributes
	 * @return
	 */
	@Override
	public String convertToDatabaseColumn(List<Object> attributes) {
		if (!CollectionUtils.isEmpty(attributes)) {
			List<WithTypeObject> typeObjects = attributes.stream().map(WithTypeObject::create).toList();
			return FormatHelper.toJson(typeObjects);
		}
		return null;
	}

	/**
	 * @param dbStr
	 * @return
	 */
	@Override
	public List<Object> convertToEntityAttribute(String dbStr) {
		List<WithTypeObject> typeObjects = FormatHelper.fromJsonAsList(dbStr, WithTypeObject.class);
		if (!CollectionUtils.isEmpty(typeObjects)){
			return typeObjects.stream().map(WithTypeObject::getRawTypeObject).toList();
		}
		return null;
	}
}
