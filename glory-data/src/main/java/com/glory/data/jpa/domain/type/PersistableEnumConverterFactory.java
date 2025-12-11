/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.type;


import com.glory.foundation.domain.PersistableEnum;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

/**
 * @author : YY
 * @date : 2025/11/20
 * @descprition :
 *
 */

@SuppressWarnings({"unchecked","rawtypes"})
public class PersistableEnumConverterFactory implements ConverterFactory<Integer, PersistableEnum> {

	/**
	 * @param targetType
	 * @param <T>
	 * @return
	 */
	@Override
	public <T extends PersistableEnum> Converter<Integer, T> getConverter(@Nonnull Class<T> targetType) {
		return new IntegerToPersistableEnumConverter(getEnumType(targetType));
	}

	private   Class<?> getEnumType(Class<?> targetType) {
		Class<?> enumType = targetType;
		while (enumType != null && !enumType.isEnum()) {
			enumType = enumType.getSuperclass();
		}
		Assert.notNull(enumType, () -> "The target type " + targetType.getName() + " does not refer to an enum");
		return enumType;
	}

	static class IntegerToPersistableEnumConverter<T extends PersistableEnum> implements Converter<Integer, T> {
		private final Class<T> clz;

		public IntegerToPersistableEnumConverter(Class<T> clz) {
			this.clz = clz;
		}

		/**
		 * @param source
		 * @return
		 */
		@Override
		public T convert(@Nonnull Integer source) {
			return PersistableEnum.resolve(source,clz);
		}
	}
}
