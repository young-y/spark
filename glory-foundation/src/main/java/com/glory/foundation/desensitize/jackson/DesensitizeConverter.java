/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.desensitize.jackson;


import com.fasterxml.jackson.databind.util.StdConverter;
import com.glory.foundation.crypto.CryptoContext;
import com.glory.foundation.desensitize.DesensitizeHelper;

/**
 * @author : YY
 * @date : 2025/12/23
 * @descprition :
 *
 */

public class DesensitizeConverter extends StdConverter<String,String> {
	private CryptoContext context;

	public DesensitizeConverter(CryptoContext context){
		this.context = context;
	}
	/**
	 * @param value
	 * @return
	 */
	@Override
	public String convert(String value) {
		context.setValue(value);
		return DesensitizeHelper.desensitize(context);
	}
}
