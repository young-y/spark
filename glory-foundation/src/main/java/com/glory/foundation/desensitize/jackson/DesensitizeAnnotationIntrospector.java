/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.desensitize.jackson;


import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.glory.foundation.crypto.CryptoContext;
import com.glory.foundation.desensitize.DesensitizeHelper;

/**
 * @author : YY
 * @date : 2025/12/18
 * @descprition :
 *
 */

public class DesensitizeAnnotationIntrospector extends JacksonAnnotationIntrospector {

	@Override
	public Object findSerializationConverter(Annotated a) {
		if (DesensitizeHelper.match(a)){
			CryptoContext context = DesensitizeHelper.createContext(a);
			if (null != context){
				return new DesensitizeConverter(context);
			}
		}
		return super.findSerializationConverter(a);
	}

}
