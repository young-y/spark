/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.md5;


import com.glory.foundation.crypto.Encryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author : YY
 * @date : 2025/12/14
 * @descprition :
 *
 */

@Configuration
public class Md5CryptoConfiguration {

	@Value("${glory.crypto.md5.formatHex:false}")
	private boolean formatHex;
	@Bean("md5Encryptor")
	@ConditionalOnMissingBean(name = "md5Encryptor")
	public Encryptor sha256Encryptor() {
		return str -> {
			if (StringUtils.hasLength(str)) {
				try {
					MessageDigest digest = MessageDigest.getInstance("MD5");
					byte[] bytes = digest.digest(str.getBytes(StandardCharsets.UTF_8));
					if (formatHex){
						return bytesToHex(bytes);
					}
					return Base64.getEncoder().encodeToString(bytes);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			return str;
		};
	}
	private String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
