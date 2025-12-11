/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.jackson.type;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.annotation.Nonnull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author : YY
 * @date : 2025/11/30
 * @descprition :
 *
 */

public class WithTypeReference {
	private Class<?> rawClass;
	private Function<Object,Object> transmit;
	private Class<?> elementClass = WithTypeObject.class;
	private Class<?> keyClass = String.class;
	private BeanProperty property;

	public WithTypeReference(@Nonnull BeanProperty property, Function<Object, Object> transmit) {
		this.property = property;
		this.transmit = transmit;
		this.rawClass = property.getType().getRawClass();
	}

	public WithTypeReference(@Nonnull BeanProperty property) {
		this.property = property;
		this.rawClass = property.getType().getRawClass();
	}

	public Class<?> getRawClass() {
		return rawClass;
	}

	public void setRawClass(Class<?> rawClass) {
		this.rawClass = rawClass;
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	public Function<Object, Object> getTransmit() {
		return null !=transmit?transmit:(objects)->{
			if (objects instanceof Map os){
				Map map = new HashMap();
				os.forEach((k,v)->{
					if (v instanceof WithTypeObject ot){
						map.put(k,ot.getRawTypeObject());
					}else {
						map.put(k,v);
					}
				});
				return  map;
			}
			return objects;
		};
	}

	public void setTransmit(Function<Object, Object> transmit) {
		this.transmit = transmit;
	}

	public Class<?> getElementClass() {
		return elementClass;
	}

	public void setElementClass(Class<?> elementClass) {
		this.elementClass = elementClass;
	}

	public Class<?> getKeyClass() {
		return keyClass;
	}

	public void setKeyClass(Class<?> keyClass) {
		this.keyClass = keyClass;
	}

	public BeanProperty getProperty() {
		return property;
	}

	public void setProperty(BeanProperty property) {
		this.property = property;
	}

	@JsonIgnore
	public JavaType getJavaType(){
		if (Map.class.isAssignableFrom(rawClass)){
			return TypeFactory.defaultInstance().constructMapType(Map.class,keyClass,elementClass);
		}else if (Collection.class.isAssignableFrom(rawClass)){
			return TypeFactory.defaultInstance().constructCollectionType((Class<? extends Collection>) rawClass,elementClass);
		}
		return TypeFactory.defaultInstance().constructType(rawClass);
	}
}
