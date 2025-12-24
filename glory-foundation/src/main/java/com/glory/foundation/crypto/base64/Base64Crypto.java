/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.base64;


import com.glory.foundation.crypto.Crypto;
import com.glory.foundation.crypto.CryptoConstant;
import com.glory.foundation.crypto.Decryptor;
import com.glory.foundation.crypto.Encryptor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/12/12
 * @descprition :
 *
 */
@Component
public class Base64Crypto implements Crypto<String> {

	@Resource(name = "base64Encryptor")
	private Encryptor encryptor;
	@Resource(name = "base64Decryptor")
	private Decryptor decryptor;
	/**
	 * @return
	 */
	@Override
	public String algorithm() {
		return CryptoConstant.CRYPTO_RW_BASE;
	}

	/**
	 * @param ciphertext
	 * @return
	 */
	@Override
	public String decrypt(String ciphertext) {
		return decryptor.decrypt(ciphertext);
	}

	/**
	 * @param str
	 * @return
	 */
	@Override
	public String encrypt(String str) {
		return encryptor.encrypt(str);
	}
}
