/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.converter;


import com.glory.foundation.crypto.CryptoHelper;
import jakarta.persistence.AttributeConverter;

/**
 * @author : YY
 * @date : 2025/12/13
 * @descprition :
 *
 */

public class SecretSystemStringConverter implements AttributeConverter<String,String> {
	/**
	 * @param attribute
	 * @return
	 */
	@Override
	public String convertToDatabaseColumn(String attribute) {
		return CryptoHelper.encryptSystem(attribute,true);
	}

	/**
	 * @param dbData
	 * @return
	 */
	@Override
	public String convertToEntityAttribute(String dbData) {
		return CryptoHelper.decryptSystem(dbData,true);
	}
}
