/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.type;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

/**
 * @author : YY
 * @date : 2025/11/28
 * @descprition :
 *
 */

public class WithTypeSerializer extends JsonSerializer<Object> implements ContextualSerializer {
	/**
	 * @param value
	 * @param gen
	 * @param provider
	 * @throws IOException
	 */
	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {

	}

	/**
	 * @param prov     Serializer provider to use for accessing config, other serializers
	 * @param property Method or field that represents the property
	 *                 (and is used to access value to serialize).
	 *                 Should be available; but there may be cases where caller cannot provide it and
	 *                 null is passed instead (in which case impls usually pass 'this' serializer as is)
	 * @return
	 * @throws JsonMappingException
	 */
	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
		if (Objects.isNull(property)){
			return prov.findNullValueSerializer(null);
		}
		WithType annotation = property.getAnnotation(WithType.class);
		if (null != annotation){
			Class<?> rawClass = property.getType().getRawClass();
			WithTypeReference reference = new WithTypeReference(property);
			Function<Object,Object> transmit = null;
			if (Collection.class.isAssignableFrom(rawClass)){
				transmit = objects -> ((Collection<?>) objects).stream().map(WithTypeObject::create).toList();
			}else if (Map.class.isAssignableFrom(rawClass)){
				transmit = objects->{
					Map<Object, WithTypeObject> typeMap = new HashMap<>();
					((Map<?,?>) objects).forEach((k,v)->{
						typeMap.put(k, WithTypeObject.create(v));
					});
					return typeMap;
				};
			}
			else {
				reference.setRawClass(WithTypeObject.class);
				transmit = WithTypeObject::create;
			}
			reference.setTransmit(transmit);
			return  new ObjectWithTypeSerializer(reference);
		}
		return prov.findValueSerializer(property.getType(),property);
	}

	public static class ObjectWithTypeSerializer extends JsonSerializer<Object>{
		private final WithTypeReference reference;

		public ObjectWithTypeSerializer(WithTypeReference reference) {
			this.reference = reference;
		}

		@Override
		public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			value = reference.getTransmit().apply(value);
			serializers.findPrimaryPropertySerializer(reference.getJavaType(),null).serialize(value, gen, serializers);
		}
	}
}
