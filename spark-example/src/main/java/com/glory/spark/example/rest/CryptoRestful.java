/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.rest;


import com.glory.foundation.FoundationConfiguration;
import com.glory.foundation.context.AppContext;
import com.glory.foundation.crypto.CryptoHelper;
import com.glory.foundation.crypto.rsa.RSAKeyGenerator;
import com.glory.foundation.crypto.rsa.RSAKeyProvider;
import com.glory.spark.example.domain.CryptoObject;
import com.glory.spark.example.domain.CryptoRequest;
import com.glory.spark.example.domain.CryptoResult;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Base64;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/12/15
 * @descprition :
 *
 */

@RestController
@RequestMapping("crypto")
public class CryptoRestful {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = "publicKeyGenerator")
	private RSAKeyGenerator privateKeyGenerator;
	@Resource(name = "privateKeyGenerator")
	private RSAKeyGenerator publicKeyGenerator;
	@Resource(name = "privateKeyProvider")
	private RSAKeyProvider privateKeyProvider;
	@Resource(name = "publicKeyProvider")
	private RSAKeyProvider publicKeyProvider;

	@PostMapping("/encrypt")
	public CryptoResult encrypt(@RequestBody CryptoRequest request){
		String encrypt = CryptoHelper.encrypt(request.getStr(), request.getAlgorithm());
		return CryptoResult.create(encrypt);
	}

	@PostMapping("/decrypt")
	public CryptoResult decrypt(@RequestBody CryptoRequest request){
		String decrypt = CryptoHelper.decrypt(request.getStr(), request.getAlgorithm());
		return CryptoResult.create(decrypt);
	}

	@GetMapping("/anno/{name}")
	public CryptoObject getCryptoPassword(@PathVariable("name") String name){
		CryptoObject obj = new CryptoObject();
		obj.setName(name);
		obj.setPassword("password");
		return obj;
	}

	@PostMapping("/test")
	public CryptoResult testCrypto(@RequestBody CryptoObject request){
		logger.info(">> name={},password ={}",request.getName(),request.getPassword());
		return CryptoResult.create(request.getPassword());
	}

	@GetMapping("/key/{slatKey}")
	public CryptoResult generateKey(@PathVariable("slatKey") String slatKey){
		String privateKey = privateKeyGenerator.generate(slatKey);
		String pubicKey = publicKeyGenerator.generate(slatKey);
		return CryptoResult.create(pubicKey,privateKey);
	}

	@GetMapping("/getKey")
	public CryptoResult getRSAKey(){
		Key publicKey = publicKeyProvider.provide();
		Key privateKey = privateKeyProvider.provide();
		return CryptoResult.create(Base64.getEncoder().encodeToString(publicKey.getEncoded()),Base64.getEncoder().encodeToString(privateKey.getEncoded()));
	}

	@PostMapping("logback")
	public CryptoResult testLogback(@RequestBody CryptoObject req){
//		AppContext.set(FoundationConfiguration.DESENSITIZE_ACTIVITY,false);
		logger.info(">> request ={}",req);
		return CryptoResult.create(req.getPassword());
	}

	@PostMapping("/testmap")
	public CryptoResult testMap(@RequestBody Map<String,Object> map){
		logger.info(">> map ={}",map);
		return CryptoResult.create("this is test");
	}
}
