/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.helper;


import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/12/8
 * @descprition :
 *
 */
@Component
public class ContentHelper {
	@Value("${spring.application.name}")
	private String appName;//current app name
	@Value("${spark.content.app-name:}")
	private String contentAppName;


	public String getAppName() {
		return appName;
	}

	public String getContentAppName() {
		return contentAppName;
	}

	public boolean isContentApp(){
		return isContentApp(getAppName());
	}

	public boolean isContentApp(@Nonnull String appName){
		return appName.equals(contentAppName);
	}
}
