/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.entity;


import com.glory.data.jpa.domain.type.PersistableEnumConverterFactory;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author : YY
 * @date : 2025/11/19
 * @descprition :
 *
 */

@SuppressWarnings({"unchecked", "rawtypes"})
public class BeanTuple implements Tuple {
	private ConversionService conversionService;
	private final Object[] tuple;
	private final Map<String, BeanTupleElement> tupleMap = new HashMap<>();

	public BeanTuple(Object[] tuple, String[] aliases) {
		Assert.isTrue(null != tuple, "Tuple must not be null");
		Assert.isTrue(null != aliases, "Aliases must not be null");
		Assert.isTrue(tuple.length == aliases.length, "Got different size of tuples and aliases");
		this.tuple = tuple;
		for (int i = 0; i < tuple.length; i++) {
			String alias = aliases[i];
			tupleMap.put(alias, new BeanTupleElement<>(alias, tuple[i]));
		}
	}

	/**
	 * @param tupleElement tuple element
	 * @param <X>
	 * @return
	 */
	@Override
	public <X> X get(TupleElement<X> tupleElement) {
		return get(tupleElement.getAlias(), tupleElement.getJavaType());
	}

	/**
	 * @param alias alias assigned to tuple element
	 * @param type  of the tuple element
	 * @param <X>
	 * @return
	 */
	@Override
	public <X> X get(String alias, Class<X> type) {
		final Object untyped = get(alias);
		return (untyped != null) ? type.cast(untyped) : null;
	}

	/**
	 * @param alias alias assigned to tuple element
	 * @return
	 */
	@Override
	public Object get(String alias) {
		BeanTupleElement beanTupleElement = tupleMap.get(alias);
		if (null != beanTupleElement) {
			return beanTupleElement.value;
		}
		return null;
	}

	/**
	 * @param i    position in result tuple
	 * @param type type of the tuple element
	 * @param <X>
	 * @return
	 */
	@Override
	public <X> X get(int i, Class<X> type) {
		final Object untyped = get(i);
		return (untyped != null) ? type.cast(untyped) : null;
	}

	/**
	 * @param i position in result tuple
	 * @return
	 */
	@Override
	public Object get(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("requested tuple index must be greater than zero");
		}
		if (i >= tuple.length) {
			throw new IllegalArgumentException("requested tuple index exceeds actual tuple size");
		}
		return tuple[i];
	}

	/**
	 * @return
	 */
	@Override
	public Object[] toArray() {
		return tuple;
	}

	/**
	 * @return
	 */
	@Override
	public List<TupleElement<?>> getElements() {
		return List.of((TupleElement<?>) tupleMap.values());
	}

	public <T> T convert(Class<T> clz) {
		T obj = BeanUtils.instantiateClass(clz);
		PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(clz);
		Stream.of(descriptors).forEach(d -> {
			String name = d.getName();
			Object value = get(name);
			Method writeMethod = d.getWriteMethod();
			if (Objects.nonNull(value) && null != writeMethod) {
				Class<?> parameterType = writeMethod.getParameterTypes()[0];
				try {
					if (!ClassUtils.isAssignable(parameterType, value.getClass())) {
						value = conversionService.convert(value, parameterType);
					}
					if (!Modifier.isPublic(writeMethod.getModifiers())) {
						writeMethod.setAccessible(true);
					}
					writeMethod.invoke(obj, value);
				} catch (Throwable ex) {
					throw new FatalBeanException(
							"Could not copy property '" + d.getName() + "' from source to target", ex);
				}
			}

		});
		return obj;
	}

	public static class BeanTupleElement<X> implements TupleElement<X> {

		private final X value;
		private final String alias;

		public BeanTupleElement(@Nonnull String alias, @Nonnull X value) {
			this.alias = alias;
			this.value = value;
		}

		@Override
		public Class<? extends X> getJavaType() {
			return (Class<? extends X>) this.value.getClass();
		}

		private Class<?> getValueClass(Object value) {
			Class<?> valueClass = Object.class;
			if (value != null) {
				valueClass = value.getClass();
			}
			return valueClass;
		}

		@Override
		public String getAlias() {
			return alias;
		}

		public X getValue() {
			return value;
		}
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
		if (this.conversionService instanceof ConverterRegistry){
			((ConverterRegistry) this.conversionService).addConverterFactory(new PersistableEnumConverterFactory());
		}
	}
}
