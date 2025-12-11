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
 * @date : 2025/11/4
 * @descprition :
 *
 */

public enum TaskStatus implements PersistableEnum<Integer> {
	Init("init", 0)  //init
	, Success("success", 1)  //success
	, Fail("fail", -1)  //fail
	, Compensate("compensate", 2);

	private final String name;
	private final Integer value;

	private TaskStatus(String name, Integer value) {
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
	public static TaskStatus fromPersistableValue(int value) {
		return PersistableEnum.valueOf(value, TaskStatus.class);
	}

	public static class TaskStatusConverter implements AttributeConverter<TaskStatus, Integer> {

		/**
		 * @param attribute
		 * @return
		 */
		@Override
		public Integer convertToDatabaseColumn(TaskStatus attribute) {
			return attribute.getValue();
		}

		/**
		 * @param dbData
		 * @return
		 */
		@Override
		public TaskStatus convertToEntityAttribute(Integer dbData) {
			return TaskStatus.fromPersistableValue(dbData);
		}
	}
}
