/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.jackson.type;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/11/28
 * @descprition :
 *
 */

public class WithTypeObject {
	//	private String className;
	private Class<?> rawClass;
	private Object rawObject;

	public void setRawClass(Class<?> rawClass) {
		this.rawClass = rawClass;
	}

	public Class<?> getRawClass() {
		return rawClass;
	}

	public void setRawObject(Object rawObject) {
		this.rawObject = rawObject;
	}

	public Object getRawObject() {
		return rawObject;
	}

	@JsonIgnore
	public static WithTypeObject create(@Nonnull Object obj) {
		WithTypeObject type = new WithTypeObject();
		type.rawClass = obj.getClass();
		type.rawObject = obj;
		return type;
	}

	@SuppressWarnings("unchecked")
	@JsonIgnore
	public Object getRawTypeObject() {
		if (rawObject instanceof Map map) {
			Object object = BeanUtils.instantiateClass(rawClass);
			try {
				map.forEach((k, v) -> {
					PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(rawClass, k.toString());
					if (null != descriptor) {
						try {
							descriptor.getWriteMethod().invoke(object, v);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});
				return object;
			} catch (Exception e) {
				return rawObject;
			}
		}
		return rawObject;
	}
}
