/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JavaType;
import com.glory.foundation.desensitize.Desensitize;

/**
 * @author : YY
 * @date : 2025/12/22
 * @descprition :
 *
 */

public class CryptoContext {
	private String name;
	private String algorithm;
	private String patten;
	private String replacement;
	private String value;
	private JavaType javaType;
	private int minLength=4;
	private boolean withPrefix = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatten() {
		return patten;
	}

	public void setPatten(String patten) {
		this.patten = patten;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public JavaType getJavaType() {
		return javaType;
	}

	public void setJavaType(JavaType javaType) {
		this.javaType = javaType;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public boolean isWithPrefix() {
		return withPrefix;
	}

	public void setWithPrefix(boolean withPrefix) {
		this.withPrefix = withPrefix;
	}

	@JsonIgnore
	public static CryptoContext create(Desensitize desensitize){
		CryptoContext context = new CryptoContext();
		context.setReplacement(desensitize.replacement());
		context.setPatten(desensitize.patten());
		context.setAlgorithm(desensitize.algorithm());
		context.setWithPrefix(desensitize.withPrefix());
		context.setMinLength(desensitize.minLength());
		return context;
	}

//	@JsonIgnore
//	public static CryptoContext create(String name){
//		CryptoContext context = new CryptoContext();
//		context.setName(name);
//		return context;
//	}
//
//	@JsonIgnore
//	public static CryptoContext context(String name, String patten, String replacement){
//		CryptoContext context = create(name);
//		context.setPatten(patten);
//		context.setReplacement(replacement);
//		return context;
//	}
}
