/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author : YY
 * @date : 2025/11/8
 * @descprition :
 *
 */

public final class AppContext {

    private final static Logger logger = LoggerFactory.getLogger(AppContext.class);
    public static final String GLORY_TENANT_CODE = "GLORY_TENANT_CODE";
    public static final String GLORY_TENANT = "GLORY_TENANT";
    public static final String GLORY_USER = "GLORY_USER";
    public static final String GLORY_USER_ID = "GLORY_USER_ID";
	public static final String GLORY_TRACE_ID = "GLORY_TRACE_ID";

    public static void setTenantCode(String tenantCode){
        GloryThreadContextHolder.set(GLORY_TENANT_CODE,tenantCode);
    }

    public static String getTenantCode(){
        BaseTenant baseTenant = getTenant();
        if (null != baseTenant){
            return baseTenant.getCode();
        }
        return GloryThreadContextHolder.get(GLORY_TENANT_CODE,null);
    }

    public static void setTenant(BaseTenant baseTenant){
        GloryThreadContextHolder.set(GLORY_TENANT, baseTenant);
    }

    public static BaseTenant getTenant(){
        return GloryThreadContextHolder.get(GLORY_TENANT,null);
    }

    public static void setUser(BaseUser user){
        GloryThreadContextHolder.set(GLORY_USER,user);
    }

    public static BaseUser getUser(){
        return GloryThreadContextHolder.get(GLORY_USER,null);
    }

    public static void setUserId(Long userId){
        GloryThreadContextHolder.set(GLORY_USER_ID,userId);
    }

    public static Long getUserId(){
        BaseUser user = getUser();
        if (null != user){
            return user.getUserId();
        }
        return GloryThreadContextHolder.get(GLORY_USER_ID,null);
    }

	public static void setTraceId(String traceId){
		GloryThreadContextHolder.set(GLORY_TRACE_ID,traceId);
	}

	public static String getTraceId(){
		if (!GloryThreadContextHolder.isExist(GLORY_TRACE_ID)){
			setTraceId(UUID.randomUUID().toString());
		}
		return GloryThreadContextHolder.get(GLORY_TRACE_ID,null);
	}

	public static  void set(String key,Object value){
		GloryThreadContextHolder.set(key,value);
	}

	public static <T> T get(String key,T dv){
		return GloryThreadContextHolder.get(key, dv);
	}
}
