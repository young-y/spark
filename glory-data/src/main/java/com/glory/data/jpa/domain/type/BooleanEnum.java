/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.type;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.glory.foundation.domain.PersistableEnum;
import jakarta.persistence.AttributeConverter;

/**
 * @author : YY
 * @date : 2025/11/3
 * @descprition :
 *
 */

public enum BooleanEnum implements PersistableEnum<Integer> {
	False("false", 0)  //success
	, True("true", 1)  //warning
	;

	private final String name;
	private final Integer value;

	private BooleanEnum(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	@Override
	@JsonValue
	public Integer value() {
		return getValue();
	}

	@JsonCreator
	public static BooleanEnum fromPersistableValue(int value) {
		return PersistableEnum.valueOf(value, BooleanEnum.class);
	}

	public static class BooleanEnumConverter implements AttributeConverter<BooleanEnum, Integer> {


		/**
		 * @param attribute
		 * @return
		 */
		@Override
		public Integer convertToDatabaseColumn(BooleanEnum attribute) {
			return attribute.getValue();
		}

		/**
		 * @param dbData
		 * @return
		 */
		@Override
		public BooleanEnum convertToEntityAttribute(Integer dbData) {
			return BooleanEnum.fromPersistableValue(dbData);
		}
	}
}
