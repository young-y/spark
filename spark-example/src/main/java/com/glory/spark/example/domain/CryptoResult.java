/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.domain;


/**
 * @author : YY
 * @date : 2025/12/15
 * @descprition :
 *
 */

public class CryptoResult {
	private String text;
	private String privateKey;
	private String publicKey;

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static CryptoResult create(String text){
		CryptoResult result = new CryptoResult();
		result.text = text;
		return result;
	}

	public static CryptoResult create(String publicKey,String privateKey){
		CryptoResult result = new CryptoResult();
		result.privateKey= privateKey;
		result.publicKey = publicKey;
		return result;
	}
}
