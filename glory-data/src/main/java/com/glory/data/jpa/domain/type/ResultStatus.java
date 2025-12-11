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

public enum ResultStatus implements PersistableEnum<Integer> {
	Success("success", 1)  //success
	, Warn("warn", -1)  //warning
	, Fail("fail", -2)  //fail
	;

	private final String name;
	private final Integer value;

	private ResultStatus(String name, Integer value) {
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
	public static ResultStatus fromPersistableValue(int value) {
		return PersistableEnum.valueOf(value, ResultStatus.class);
	}

	public static class ResultStatusConverter implements AttributeConverter<ResultStatus, Integer> {


		/**
		 * @param attribute
		 * @return
		 */
		@Override
		public Integer convertToDatabaseColumn(ResultStatus attribute) {
			return attribute.getValue();
		}

		/**
		 * @param dbData
		 * @return
		 */
		@Override
		public ResultStatus convertToEntityAttribute(Integer dbData) {
			return ResultStatus.fromPersistableValue(dbData);
		}
	}
}
