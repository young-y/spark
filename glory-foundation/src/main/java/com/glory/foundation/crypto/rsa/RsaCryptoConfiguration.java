/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.crypto.rsa;


import com.glory.foundation.crypto.Decryptor;
import com.glory.foundation.crypto.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
public class RsaCryptoConfiguration {

	private static final String ALGORITHM = "RSA";
	@Autowired
	private Environment environment;
	private final Map<Integer, Cipher> cipherMap = new HashMap<>(8);


	@Bean("publicKeyGenerator")
	@ConditionalOnMissingBean(name = "publicKeyGenerator")
	public RSAKeyGenerator publicKeyGenerator(){
		return key->{
			Assert.hasLength(key," key is empty.");
			try{
				return Base64.getEncoder().encodeToString(generator(key).getPublic().getEncoded());
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		};
	}

	@Bean("privateKeyGenerator")
	@ConditionalOnMissingBean(name = "privateKeyGenerator")
	public RSAKeyGenerator privateKeyGenerator(){
		return key->{
			Assert.hasLength(key," key is empty.");
			try{
				return Base64.getEncoder().encodeToString(generator(key).getPrivate().getEncoded());
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		};
	}

	@Bean("publicKeyProvider")
	@ConditionalOnMissingBean(name = "publicKeyProvider")
	public RSAKeyProvider publicKeyProvider(){
		return ()->{
			try {
				String key = getKeyString("public");
				Assert.hasLength(key,"Public key is empty.");
				return KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(key)));
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		};
	}

	@Bean("privateKeyProvider")
	@ConditionalOnMissingBean(name = "privateKeyProvider")
	public RSAKeyProvider privateKeyProvider(){
		return ()->{
			try {
				String key = getKeyString("private");
				Assert.hasLength(key,"Private key is empty.");
				return KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key)));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	@Bean("rasEncryptor")
	@ConditionalOnMissingBean(name = "rasEncryptor")
	public Encryptor<String> rasEncryptor(@Qualifier("publicKeyProvider") RSAKeyProvider provider){
		return str -> {
			if (StringUtils.hasLength(str)){
				try{
					Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, provider.provide());
					byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
					return Base64.getEncoder().encodeToString(bytes);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			return str;
		};
	}
	@Bean("rsaDecryptor")
	@ConditionalOnMissingBean(name = "rsaDecryptor")
	public Decryptor rsaDecryptor(@Qualifier("privateKeyProvider") RSAKeyProvider provider){
		return ciphertext -> {
			if (StringUtils.hasLength(ciphertext)){
				try {
					Cipher cipher = getCipher(Cipher.DECRYPT_MODE, provider.provide());
					byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
					return new String(bytes,StandardCharsets.UTF_8);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			return ciphertext;
		};
	}

	private String getKeyString(String category) throws IOException {
		String envKey = "glory.secret.rsa."+category+".key";
		String property = environment.getProperty(envKey);
		if (!StringUtils.hasLength(property)){
			envKey = "glory.secret.rsa."+category+".file";
			String file = environment.getProperty(envKey);
			Assert.hasLength(file,category+"Key has missed.");
			Resource resource = new ClassPathResource(file);
			return resource.getContentAsString(StandardCharsets.UTF_8);
		}
		return property;
	}

	private Cipher getCipher(int cipherMode,Key key) throws Exception {
		Cipher cipher = cipherMap.get(cipherMode);
		if (null == cipher){
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(cipherMode,key);
			cipherMap.put(cipherMode,cipher);
		}
		return cipher;
	}

	private KeyPair generator(String slatKey) throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(slatKey.getBytes(StandardCharsets.UTF_8));
		generator.initialize(1 << 11, random);
		return generator.generateKeyPair();
	}

}
