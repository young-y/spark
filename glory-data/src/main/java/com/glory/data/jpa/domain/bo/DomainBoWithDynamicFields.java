/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.bo;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.data.jpa.domain.DynamicFieldSupport;
import com.glory.foundation.type.WithType;
import jakarta.annotation.Nonnull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/11
 * @descprition :
 *
 */

public abstract class DomainBoWithDynamicFields extends DomainBo implements DynamicFieldSupport {
	@WithType
	private final Map<String, Object> dynamicFields = new HashMap<>(8);

	@JsonAnyGetter
	public Map<String, Object> getDynamicFields() {
		return dynamicFields;
	}

	@JsonIgnore
	public void setDynamicFields(Map<String, Object> dynamicFields) {
		Optional.ofNullable(dynamicFields).ifPresent(this.dynamicFields::putAll);
	}

	@Override
	@JsonAnySetter
	public void setFieldValue(@Nonnull String key, Object value) {
		if (!this.dynamicFields.containsKey(key)){
			this.dynamicFields.put(key, value);
		}
	}

	@Override
	@JsonIgnore
	public Object getFieldValue(@Nonnull String key) {
		return this.dynamicFields.get(key);
	}

}
