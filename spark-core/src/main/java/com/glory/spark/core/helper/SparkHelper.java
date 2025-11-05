/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.helper;


import org.springframework.beans.factory.annotation.Value;
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
    private String appName;
    @Value("${server.context-path}:")
    private String contextPath;
    @Value("${spark.snapshot.master-app-name}:")
    private String masterAppName;
    @Value("${spark.snapshot.enabled}:true")
    private boolean snapshot;
    @Value("{spark.snapshot.deploy.master}:true")
    private boolean deployMaster;
    @Value("{spark.snapshot.develop.local}:false")
    private boolean develop;

    public String getAppName() {
        return appName;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getMasterAppName() {
        if (deployMaster){
            Assert.hasLength(masterAppName,"At master mode,the masterAppName can't empty.");
        }
        return masterAppName;
    }

    public boolean isDeployMaster() {
        return deployMaster;
    }

    public boolean isDevelop() {
        return develop;
    }

    public boolean isSnapshot() {
        return snapshot;
    }

    public boolean isMasterApp(){
        return deployMaster&&getAppName().equalsIgnoreCase(getMasterAppName());
    }

    public String nextSerialId(){
        return UUID.fromString("SPARK").toString();
    }
}
