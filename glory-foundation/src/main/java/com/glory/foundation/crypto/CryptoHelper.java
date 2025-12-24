/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto;


import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/12/11
 * @descprition :
 *
 */
@Component
public class CryptoHelper {

	private  static String defaultAlgorithm;

	private final static Map<String, Crypto> cryptoMap = new HashMap<>(8);

	public static Crypto getCrypto(String algorithm) {
		if (StringUtils.hasLength(algorithm)){
			return cryptoMap.get(algorithm);
		}
		return cryptoMap.get(defaultAlgorithm);
	}

	public static String encryptSystem(@Nonnull String src, boolean withPrefix) {
		return encrypt(src,defaultAlgorithm,withPrefix);
	}
	public static String encrypt(@Nonnull String src,  String algorithm) {
		return encrypt(src, algorithm, false);
	}

	public static String encrypt(@Nonnull String src, String algorithm, boolean withPrefix) {
		Crypto crypto = getCrypto(algorithm);
		Assert.notNull(crypto, algorithm + " can't found Crypto.");
		return getPrefix(algorithm, withPrefix) + crypto.encrypt(src);
	}

	public static String decryptSystem(@Nonnull String ciphertext, boolean withPrefix) {
		return decrypt(ciphertext,defaultAlgorithm,withPrefix);
	}
	public static String decrypt(@Nonnull String ciphertext, String algorithm) {
		return decrypt(ciphertext, algorithm, false);
	}

	public static String decrypt(@Nonnull String ciphertext,  String algorithm, boolean withPrefix) {
		Crypto crypto = getCrypto(algorithm);
		Assert.notNull(crypto, algorithm + " can't found Crypto.");
		if (withPrefix) {
			String prefix = getPrefix(algorithm, withPrefix);
			if (ciphertext.startsWith(prefix)){
				ciphertext = ciphertext.substring(prefix.length());
			}else {
				return ciphertext;
			}
		}
		return crypto.decrypt(ciphertext);
	}

	private static String getPrefix(String algorithm, boolean withPrefix) {
		StringBuilder builder = new StringBuilder();
		if (withPrefix) {
			builder.append("{").append(algorithm).append("}");
		}
		return builder.toString();
	}

	public static String encryptAES(@Nonnull String src) {
		return encrypt(src, CryptoConstant.CRYPTO_RW_AES);
	}

	public static String decryptAES(@Nonnull String ciphertext) {
		return decrypt(ciphertext, CryptoConstant.CRYPTO_RW_AES);
	}

	public static String encryptBase64(@Nonnull String src) {
		return encrypt(src, CryptoConstant.CRYPTO_RW_BASE);
	}

	public static String decryptBase64(@Nonnull String ciphertext) {
		return decrypt(ciphertext, CryptoConstant.CRYPTO_RW_BASE);
	}

	@Autowired
	public void setCryptos(List<Crypto> cryptos) {
		Optional.ofNullable(cryptos).ifPresent(cs -> {
			cs.forEach(crypto -> {
				cryptoMap.put(crypto.algorithm(), crypto);
			});
		});
	}

	@Value("${glory.secret.algorithm.default:AES}")
	public void setDefaultAlgorithm(String defaultAlgorithm) {
		CryptoHelper.defaultAlgorithm = defaultAlgorithm;
	}
}
