/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.domain.type;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.glory.foundation.domain.PersistableEnum;
import jakarta.persistence.AttributeConverter;

/**
 * @author : YY
 * @date : 2025/10/28
 * @descprition :
 *
 */

public enum SourceFrom implements PersistableEnum<Integer> {
	Online("online", 1)  //option of flow
	, Batch("batch", 2)  //batch schedule
	, Compensate("compensate", 3)  //re-generate  after
	;

	private final String name;
	private final Integer value;

	private SourceFrom(String name, Integer value) {
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
	public static SourceFrom fromPersistableValue(int value) {
		return PersistableEnum.valueOf(value, SourceFrom.class);
	}

	public static class SourceFromConverter implements AttributeConverter<SourceFrom, Integer> {

		/**
		 * @param attribute
		 * @return
		 */
		@Override
		public Integer convertToDatabaseColumn(SourceFrom attribute) {
			return attribute.getValue();
		}

		/**
		 * @param dbData
		 * @return
		 */
		@Override
		public SourceFrom convertToEntityAttribute(Integer dbData) {
			return SourceFrom.fromPersistableValue(dbData);
		}
	}
}
