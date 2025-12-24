/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.des;


import com.glory.foundation.crypto.Crypto;
import com.glory.foundation.crypto.CryptoConstant;
import com.glory.foundation.crypto.Decryptor;
import com.glory.foundation.crypto.Encryptor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/12/14
 * @descprition :
 *
 */

@Component
public class DesCrypto implements Crypto<String> {

	@Resource(name = "desEncryptor")
	private Encryptor encryptor;
	@Resource(name = "desDecryptor")
	private Decryptor decryptor;
	/**
	 * @return
	 */
	@Override
	public String algorithm() {
		return CryptoConstant.CRYPTO_RW_DES;
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
