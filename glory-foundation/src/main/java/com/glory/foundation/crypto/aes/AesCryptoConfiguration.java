/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.aes;


import com.glory.foundation.crypto.Decryptor;
import com.glory.foundation.crypto.Encryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/12/12
 * @descprition :
 *
 */
@Configuration
public class AesCryptoConfiguration {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String ALGORITHM = "AES";
	@Value("${glory.crypto.aes.key:12345690ABCDEFSKDFJKSDFODSKFKKSD}")
	private String secretKey;
	@Value("${glory.crypto.aes.iv:1234567890ABCDEF}")
	private String iv;

	private final Map<Integer,Cipher> cipherMap = new HashMap<>(8);

	@Bean("aesEncryptor")
	@ConditionalOnMissingBean(name = "aesEncryptor")
	public Encryptor aesEncryptor(){
		return str->{
			if (StringUtils.hasLength(str)){
				try{
					Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
					byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
					return Base64.getEncoder().encodeToString(bytes);
				}catch (Throwable t){
					logger.warn(">> AES encrypt[{}] exception:",str,t);
					throw new RuntimeException(t);
				}
			}
			return str;
		};
	}

	@Bean("aesDecryptor")
	@ConditionalOnMissingBean(name = "aesDecryptor")
	public Decryptor aesDecryptor(){
		return ciphertext -> {
			if (StringUtils.hasLength(ciphertext)){
				try{
					Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
					byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
					return new String(bytes,StandardCharsets.UTF_8);
				}catch (Throwable t){
					logger.warn(">> AES decrypt[{}] exception:",ciphertext,t);
					throw new RuntimeException(t);
				}
			}
			return ciphertext;
		};
	}

	private Cipher getCipher(int cryptMode) throws Exception {
		Cipher cipher = cipherMap.get(cryptMode);
		if (null == cipher){
			Assert.hasLength(secretKey,"Secret key is empty.");
			Assert.isTrue(secretKey.length() ==32,"Secret key must be 32 bit.");
			Assert.notNull(iv,"Iv is empty.");
			Assert.isTrue(iv.length() ==16,"Iv must be 16 bit.");
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			final SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
			final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
			cipher.init(cryptMode, keySpec, ivParameterSpec);
			cipherMap.put(cryptMode,cipher);
		}
		return cipher;
	}
}
