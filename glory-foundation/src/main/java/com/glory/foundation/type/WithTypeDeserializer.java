/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.type;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : YY
 * @date : 2025/11/29
 * @descprition :
 *
 */

public class WithTypeDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {
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

		return null;
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
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		if (Objects.nonNull(property)) {
			WithType annotation = property.getAnnotation(WithType.class);
			if (null != annotation) {
				Class<?> rawClass = property.getType().getRawClass();
				WithTypeReference reference = new WithTypeReference(property);
				Function<Object, Object> transmit = null;
				if (Set.class.isAssignableFrom(rawClass)) {
					transmit = (objects) -> {
						if (objects instanceof Set os) {
							return os.stream().map(o -> {
								if (o instanceof WithTypeObject ot) {
									return ot.getRawTypeObject();
								}
								return o;
							}).collect(Collectors.toSet());
						}
						return objects;
					};
				} else if (List.class.isAssignableFrom(rawClass)) {
					transmit = (objects) -> {
						if (objects instanceof List os) {
							return os.stream().map(o -> {
								if (o instanceof WithTypeObject ot) {
									return ot.getRawTypeObject();
								}
								return o;
							}).toList();
						}
						return objects;
					};
				} else if (Map.class.isAssignableFrom(rawClass)) {
					transmit = (objects) -> {
						if (objects instanceof Map os) {
							Map map = new HashMap();
							os.forEach((k, v) -> {
								if (v instanceof WithTypeObject ot) {
									map.put(k, ot.getRawTypeObject());
								} else {
									map.put(k, v);
								}
							});
							return map;
						}
						return objects;
					};
				}else {
					reference.setRawClass(WithTypeObject.class);
					transmit = object -> {
						if (object instanceof WithTypeObject ot){
								return ot.getRawTypeObject();
						}
						return object;
					};
				}
				reference.setTransmit(transmit);
				return new ObjectWithTypeDeserializer(reference);
			}
		}
		return ctxt.findContextualValueDeserializer(property.getType(), property);
	}

	public static class ObjectWithTypeDeserializer extends JsonDeserializer<Object> {

		private final WithTypeReference reference;

		public ObjectWithTypeDeserializer(WithTypeReference reference) {
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
			return reference.getTransmit().apply(object);
		}
	}
}
