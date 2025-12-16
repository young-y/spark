/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.des;


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
 * @date : 2025/12/14
 * @descprition :
 *
 */
@Configuration
public class DesCryptoConfiguration {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String ALGORITHM = "DES";
	private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";
	private  final Map<Integer,Cipher> cipherMap = new HashMap<>(8);
	@Value("${glory.crypto.des.key:1234ABCD}")
	private String secretKey;
	@Value("${glory.crypto.des.iv:ABCD1234}")
	private String ivParameter;

	@Bean("desEncryptor")
	@ConditionalOnMissingBean(name = "desEncryptor")
	public Encryptor desEncryptor(){
		return str -> {
			if (StringUtils.hasLength(str)){
				try {
					Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
					byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
					return Base64.getEncoder().encodeToString(bytes);
				} catch (Exception e) {
					logger.warn(">> DES encrypt[{}] exception:",str,e);
					throw new RuntimeException(e);
				}
			}
			return str;
		};
	}

	@Bean("desDecryptor")
	@ConditionalOnMissingBean(name = "desDecryptor")
	public Decryptor desDecryptor(){
		return ciphertext -> {
			if (StringUtils.hasLength(ciphertext)){
				try {
					Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
					byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
					return new String(bytes,StandardCharsets.UTF_8);
				} catch (Exception e) {
					logger.warn(">> DES decrypt[{}] exception:",ciphertext,e);
					throw new RuntimeException(e);
				}
			}
			return ciphertext;
		};
	}


	private  Cipher getCipher(int mode) throws Exception {
		Cipher cipher = cipherMap.get(mode);
		if (null == cipher){
			Assert.notNull(secretKey,"Secret key is empty.");
			Assert.notNull(ivParameter,"IV parameter is empty.");
			Assert.isTrue(secretKey.length() == 8,"Secret key length must be 8 bytes.");
			Assert.isTrue(ivParameter.length() == 8,"Iv parameter length must be 8 bytes.");
			cipher = Cipher.getInstance(TRANSFORMATION);
			SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),ALGORITHM);
			IvParameterSpec spec = new IvParameterSpec(ivParameter.getBytes(StandardCharsets.UTF_8));
			cipher.init(mode,keySpec,spec);
		}
		return cipher;
	}


}
