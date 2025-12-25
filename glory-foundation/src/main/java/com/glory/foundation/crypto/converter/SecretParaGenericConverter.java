/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.converter;


import com.glory.foundation.crypto.CryptoHelper;
import com.glory.foundation.crypto.annotation.Secret;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : YY
 * @date : 2025/12/25
 * @descprition :
 *
 */
@Component
public class SecretParaGenericConverter implements GenericConverter {
	/**
	 * @return
	 */
	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Set.of(new ConvertiblePair(String.class,String.class));
	}

	/**
	 * @param source     the source object to convert (may be {@code null})
	 * @param sourceType the type descriptor of the field we are converting from
	 * @param targetType the type descriptor of the field we are converting to
	 * @return
	 */
	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Secret secret = targetType.getAnnotation(Secret.class);
		if (source instanceof String &&null != secret){
			return CryptoHelper.decrypt((String) source,secret.algorithm());
		}
		return source;
	}
}
