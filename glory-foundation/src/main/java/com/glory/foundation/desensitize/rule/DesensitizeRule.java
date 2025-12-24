/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.desensitize.rule;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.foundation.crypto.CryptoContext;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/23
 * @descprition :
 *
 */

public class DesensitizeRule {
	private String algorithm;
	private String patten;
	private String replacement;
	private int minLength = 4;
	private boolean withPrefix = false;
	private List<String> names;
	private List<String> excludes;

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
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

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	@JsonIgnore
	public boolean include(String name){
		return null == excludes || !excludes.contains(name);
	}

	@JsonIgnore
	public boolean match(String name){
		return include(name) && null != names && names.contains(name);
	}

	@JsonIgnore
	public CryptoContext generate(){
		CryptoContext context = new CryptoContext();
		context.setAlgorithm(algorithm);
		context.setPatten(patten);
		context.setReplacement(replacement);
		context.setMinLength(minLength);
		context.setWithPrefix(withPrefix);
		return context;
	}
}
