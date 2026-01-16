/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.helper;


import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2026/01/9
 * @descprition :
 *
 */
@Component
public class ResourceHelper {
	@Value("${spring.application.name}")
	private String appName;//current app name
	@Value("${spark.resource.app-name:}")
	private String resourceAppName;


	public String getAppName() {
		return appName;
	}

	public String getResourceAppName() {
		return resourceAppName;
	}

	public boolean isContentApp(){
		return isContentApp(getAppName());
	}

	public boolean isContentApp(@Nonnull String appName){
		return appName.equals(resourceAppName);
	}
}
