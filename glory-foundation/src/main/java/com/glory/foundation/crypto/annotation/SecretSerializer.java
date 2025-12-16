/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.annotation;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.glory.foundation.crypto.CryptoHelper;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : YY
 * @date : 2025/12/13
 * @descprition :
 *
 */

public class SecretSerializer extends JsonSerializer<String> implements ContextualSerializer {
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
		if (Objects.isNull(property)){
			return prov.findNullValueSerializer(null);
		}
		Secret secret = property.getAnnotation(Secret.class);
		if (null !=secret ){
			return new SecretAnnotationSerializer(new SecretReference(secret,property.getType()));
		}
		return prov.findValueSerializer(property.getType(),property);
	}

	public static class SecretAnnotationSerializer extends JsonSerializer<Object>{

		private final SecretReference reference;

		public SecretAnnotationSerializer(SecretReference reference) {
			this.reference = reference;
		}

		/**
		 * @param value
		 * @param gen
		 * @param serializers
		 * @throws IOException
		 */
		@Override
		public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			if (value instanceof String){
				 value = CryptoHelper.encrypt(value.toString(), reference.getSecret().algorithm(),true);
			}
			serializers.findPrimaryPropertySerializer(reference.getJavaType(),null).serialize(value, gen, serializers);
		}
	}
}
