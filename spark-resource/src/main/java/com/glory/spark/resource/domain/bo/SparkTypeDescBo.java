/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.bo;


import jakarta.persistence.Convert;
import org.hibernate.type.NumericBooleanConverter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2026/1/12
 * @descprition :
 *
 */

public class SparkTypeDescBo {
	private String sparkCode;
	private String type;
	@Convert(converter = NumericBooleanConverter.class)
	private boolean enabled = true;
	@Convert(converter = NumericBooleanConverter.class)
	private boolean sync = true;
	private String exceptionStrategy;
	private LocalDateTime startEffectiveTime;
	private LocalDateTime endEffectiveTime;
	private final Map<String, Object> properties = new HashMap<>(16);

	public String getSparkCode() {
		return sparkCode;
	}

	public void setSparkCode(String sparkCode) {
		this.sparkCode = sparkCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isSync() {
		return sync;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	public String getExceptionStrategy() {
		return exceptionStrategy;
	}

	public void setExceptionStrategy(String exceptionStrategy) {
		this.exceptionStrategy = exceptionStrategy;
	}

	public LocalDateTime getStartEffectiveTime() {
		return startEffectiveTime;
	}

	public void setStartEffectiveTime(LocalDateTime startEffectiveTime) {
		this.startEffectiveTime = startEffectiveTime;
	}

	public LocalDateTime getEndEffectiveTime() {
		return endEffectiveTime;
	}

	public void setEndEffectiveTime(LocalDateTime endEffectiveTime) {
		this.endEffectiveTime = endEffectiveTime;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}
}
