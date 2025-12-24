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

import java.util.HashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/12/1
 * @descprition :
 *
 */

public class WithTypeMapConverter implements AttributeConverter<Map<String,Object>, String> {
	/**
	 * @param attribute
	 * @return
	 */
	@Override
	public String convertToDatabaseColumn(Map<String, Object> attribute) {
		if (!CollectionUtils.isEmpty(attribute)){
			Map<String,Object> tmp = new HashMap<>();
			attribute.forEach((k,v)->{
				if (v != null){
					tmp.put(k,WithTypeObject.create(v));
				}
			});
			return FormatHelper.toJson(tmp);
		}
		return null;
	}

	/**
	 * @param dbData
	 * @return
	 */
	@Override
	public Map<String, Object> convertToEntityAttribute(String dbData) {
		Map<String, WithTypeObject> typeObjectMap = FormatHelper.fromJsonAsMap(dbData, String.class, WithTypeObject.class);
		if (!CollectionUtils.isEmpty(typeObjectMap)){
			Map<String,Object> tmp = new HashMap<>();
			typeObjectMap.forEach((k,v)->{
				tmp.put(k,v.getRawTypeObject());
			});
			return tmp;
		}
		return null;
	}
}
