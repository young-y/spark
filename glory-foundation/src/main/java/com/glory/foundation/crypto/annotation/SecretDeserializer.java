/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.annotation;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.glory.foundation.crypto.CryptoHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : YY
 * @date : 2025/12/13
 * @descprition :
 *
 */

public class SecretDeserializer extends JsonDeserializer<String> implements ContextualDeserializer {
	/**
	 * @param p    Parser used for reading JSON content
	 * @param ctxt Context that can be used to access information about
	 *             this deserialization activity.
	 * @return
	 * @throws IOException
	 * @throws JacksonException
	 */
	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		return "";
	}

	/**
	 * @param ctxt     Deserialization context to access configuration, additional
	 *                 deserializers that may be needed by this deserializer
	 * @param property Method, field or constructor parameter that represents the property
	 *                 (and is used to assign deserialized value).
	 *                 Should be available; but there may be cases where caller cannot provide it and
	 *                 null is passed instead (in which case impls usually pass 'this' deserializer as is)
	 * @return
	 * @throws JsonMappingException
	 */
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		if(Objects.nonNull(property)){
			Secret secret = property.getAnnotation(Secret.class);
			if (null != secret){
				return new SecretAnnotationDeserializer(new SecretReference(secret, property.getType()));
			}
		}
		return ctxt.findContextualValueDeserializer(property.getType(), property);
	}

	public static class SecretAnnotationDeserializer extends JsonDeserializer<Object>{
		private Logger logger = LoggerFactory.getLogger(getClass());
		private final SecretReference reference;

		public SecretAnnotationDeserializer(SecretReference reference) {
			this.reference = reference;
		}

		/**
		 * @param p    Parser used for reading JSON content
		 * @param ctxt Context that can be used to access information about
		 *             this deserialization activity.
		 * @return
		 * @throws IOException
		 * @throws JacksonException
		 */
		@Override
		public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
			JsonDeserializer<Object> deserializer = ctxt.findContextualValueDeserializer(reference.getJavaType(), null);
			Object object = deserializer.deserialize(p, ctxt);
			if (Objects.nonNull(object)){
				Secret secret = reference.getSecret();
				if (object instanceof String){
					return CryptoHelper.decrypt(object.toString(), secret.algorithm(),true);
				}else {
					logger.info("Secret deserializer type can't support.");
				}
			}
			return object;
		}
	}
}
