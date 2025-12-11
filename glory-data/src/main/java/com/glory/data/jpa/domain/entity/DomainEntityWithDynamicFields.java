/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.entity;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.glory.foundation.jackson.type.converter.WithTypeMapConverter;
import com.glory.data.jpa.domain.DynamicFieldSupport;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */
@MappedSuperclass
public abstract class DomainEntityWithDynamicFields extends DomainEntity implements DynamicFieldSupport {

    @Column(name = "dynamic_fields")
    @Convert(converter = WithTypeMapConverter.class)
    private Map<String, Object> _dynamicFields = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getDynamicFields() {
        return _dynamicFields;
    }

    public void setDynamicFields(Map<String, Object> dynamicFields) {
		Optional.ofNullable(dynamicFields).ifPresent(this._dynamicFields::putAll);
    }

    @Override
    public Object getFieldValue(@Nonnull String key) {
        return this._dynamicFields.get(key);
    }

    @JsonAnySetter
    @Override
    public void setFieldValue(@Nonnull String key, Object value) {
		if (this._dynamicFields.containsKey(key)){
			this._dynamicFields.put(key, value);
		}
    }

}
