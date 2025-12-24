/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.desensitize.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.glory.foundation.desensitize.Desensitize;
import com.glory.foundation.crypto.CryptoContext;
import com.glory.foundation.desensitize.DesensitizeHelper;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : YY
 * @date : 2025/12/22
 * @descprition :
 *
 */

public class DesensitizeSerializer extends JsonSerializer<String> implements ContextualSerializer {
	/**
	 * @param value
	 * @param gen
	 * @param serializers
	 * @throws IOException
	 */
	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

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
		if (Objects.isNull(property)) {
			return prov.findNullValueSerializer(null);
		}
		Desensitize desensitize = property.getAnnotation(Desensitize.class);
		if (null != desensitize) {
			CryptoContext context = CryptoContext.create(desensitize);
			context.setJavaType(property.getType());
			context.setName(property.getName());
			return new DesensitizeAnnotationSerializer(context);
		}
		return prov.findValueSerializer(property.getType(), property);
	}

	public static class DesensitizeAnnotationSerializer extends JsonSerializer<Object> {
		private CryptoContext context;

		public DesensitizeAnnotationSerializer(CryptoContext context) {
			this.context = context;
		}


		@Override
		public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			if (value instanceof String){
				context.setValue((String) value);
				value= DesensitizeHelper.desensitize(context);
			}
			serializers.findPrimaryPropertySerializer(context.getJavaType(),null).serialize(value,gen,serializers);
		}

	}
}
