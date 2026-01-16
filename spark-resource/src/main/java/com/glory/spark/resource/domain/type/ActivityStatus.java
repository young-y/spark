/*
 * Copyright (c) 2025-2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.type;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.glory.foundation.domain.PersistableEnum;
import jakarta.persistence.AttributeConverter;

/**
 * @author : YY
 * @date : 2025/11/4
 * @descprition :
 *
 */

public enum ActivityStatus implements PersistableEnum<Integer> {
	Enabled("enabled", 1)  //init
	, Disabled("disabled", 0)  //success
	;

	private final String name;
	private final Integer value;

	private ActivityStatus(String name, Integer value) {
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
	public static ActivityStatus fromPersistableValue(int value) {
		return PersistableEnum.valueOf(value, ActivityStatus.class);
	}

	public static class ActivityStatusConverter implements AttributeConverter<ActivityStatus, Integer> {

		/**
		 * @param attribute
		 * @return
		 */
		@Override
		public Integer convertToDatabaseColumn(ActivityStatus attribute) {
			return attribute.getValue();
		}

		/**
		 * @param dbData
		 * @return
		 */
		@Override
		public ActivityStatus convertToEntityAttribute(Integer dbData) {
			return ActivityStatus.fromPersistableValue(dbData);
		}
	}
}
