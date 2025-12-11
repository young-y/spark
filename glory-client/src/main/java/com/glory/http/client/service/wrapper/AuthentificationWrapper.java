/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.wrapper;


import org.springframework.util.StringUtils;

import java.util.Optional; /**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */

public class AuthentificationWrapper {
	public static final String AUTH_2 = "Auth2";
	public static final String BASE_64 = "Base64";
	private String type;
	private String target;
	private String token;
	private String tokenPrefix;
	private String user;
	private String password;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getToken() {
		return token;
	}

	public String getFullToken(){
		if (StringUtils.hasLength(token)){
			if (StringUtils.hasLength(tokenPrefix)){
				return tokenPrefix+token;
			}
			return token;
		}
		return "";
	}

	public void setToken(String token) {
		Optional.ofNullable(token).ifPresent(t->{
			if (t.contains(" ")){
				String[] ts = t.split(" ");
				this.token = ts[1];
				this.tokenPrefix = ts[0];
			}else {
				this.token = t;
			}
		});
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static AuthentificationWrapper auth2() {
		AuthentificationWrapper wrapper = new AuthentificationWrapper();
		wrapper.type = AUTH_2;
		wrapper.tokenPrefix = "Bearer ";
		return wrapper;
	}

	public static AuthentificationWrapper base64() {
		AuthentificationWrapper wrapper = new AuthentificationWrapper();
		wrapper.type = BASE_64;
		wrapper.tokenPrefix = "Basic ";
		return wrapper;
	}
}
