/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.helper;


import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * @author : YY
 * @date : 2025/11/4
 * @descprition :
 *
 */

@Component
public class SparkHelper {
    @Value("${spring.application.name}")
    private String appName;//current app name
    @Value("${server.servlet.context-path:}")
    private String contextPath;//current app contextPath if setting
    @Value("${spark.snapshot.master-app-name:}")
    private String masterAppName;// it' the app name of master node  if deploy master mode.
	@Value("${spark.content.app-name:}")
	private String contentAppName;
	@Value("${spark.snapshot.enabled:true}")
    private boolean snapshot;//whether record the snapshot data
    @Value("${spark.snapshot.deploy.master:true}")
    private boolean deployMaster;// if deploy master

    @Value("${spark.filter.compensate.validate:false}")
    private boolean validateCompensate;
	@Autowired
	private Environment environment;
    public String getAppName() {
        return appName;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getMasterAppName() {
        if (deployMaster){//the master app name can't empty when deploy is master mode
            Assert.hasLength(masterAppName,"At master mode,the masterAppName can't empty.");
        }else {
			return appName;//when distributed, the master is current app
		}
        return masterAppName;
    }

    public boolean isDeployMaster() {
        return deployMaster;
    }

    public boolean isSnapshot() {
        return snapshot;
    }

	public String getContentAppName() {
		return contentAppName;
	}

	/**
	 *
	 * @param appName
	 * @return
	 */
	public boolean isCurrentApp(@Nonnull String appName){
		return appName.equalsIgnoreCase(getAppName());
	}

    public boolean isValidateCompensate() {
        return validateCompensate;
    }

    public String nextSerialId(){
        return UUID.randomUUID().toString();
    }
}
