/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.domain;


import java.util.*;

/**
 * @author : YY
 * @date : 2025/11/28
 * @descprition :
 *
 */

public class JacksonObject {
	private String name;
//	@WithType
	private Map<String,Object> properties = new HashMap<>();

//	@WithType
	private List<Object> list= new ArrayList<>();
//	@WithType
	private Set<Object>  set = new HashSet<>();

	private Map<String,Object>  parameters = new HashMap<>();

	private WorkElement<Object> element;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public Set<Object> getSet() {
		return set;
	}

	public void setSet(Set<Object> set) {
		this.set = set;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public WorkElement getElement() {
		return element;
	}

	public void setElement(WorkElement element) {
		this.element = element;
	}
}
