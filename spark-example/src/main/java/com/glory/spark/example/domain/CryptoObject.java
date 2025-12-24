/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.domain;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.foundation.crypto.annotation.Secret;
import com.glory.foundation.desensitize.Desensitize;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/12/15
 * @descprition :
 *
 */

public class CryptoObject {

	@Desensitize
	private String name;
	@Secret
	private String password;
	private Map<String,Object> properties = new HashMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonAnyGetter
	public Map<String, Object> getProperties() {
		return properties;
	}

	@JsonIgnore
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	@JsonAnySetter
	public void addProperty(String key,Object value){
		this.properties.put(key, value);
	}
}
