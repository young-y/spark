/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.base64;


import com.glory.foundation.crypto.Decryptor;
import com.glory.foundation.crypto.Encryptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author : YY
 * @date : 2025/12/12
 * @descprition :
 *
 */

@Configuration
public class Base64CryptoConfiguration {

	@Bean("base64Encryptor")
	@ConditionalOnMissingBean(name = "base64Encryptor")
	public Encryptor<String> base64Encryptor() {
		return str -> {
			if (StringUtils.hasLength(str)) {
				return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
			}
			return str;
		};
	}

	@Bean("base64Decryptor")
	@ConditionalOnMissingBean(name = "base64Decryptor")
	public Decryptor base64Decryptor() {
		return ciphertext -> {
			if (StringUtils.hasLength(ciphertext)) {
				return new String(Base64.getDecoder().decode(ciphertext));
			}
			return ciphertext;
		};
	}
}
