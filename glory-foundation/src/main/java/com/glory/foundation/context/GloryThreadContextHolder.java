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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */

public class GloryThreadContextHolder {

    private static final ThreadLocal<Map<String,Object>> contextHolder = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(GloryThreadContextHolder.class);

    public static void set(String key,Object value){
        Map<String, Object> threadMap = contextHolder.get();
        if (null == threadMap){
            threadMap = new HashMap<>(16);
            contextHolder.set(threadMap);
        }
        threadMap.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key,T dv){
        Map<String,Object> threadMap  = contextHolder.get();
        if (null != threadMap && threadMap.containsKey(key)){
            return (T) threadMap.get(key);
        }
        logger.debug("Can't find value by key {}",key);
        return dv;
    }

    public static Object remove(String key){
        Map<String,Object> threadMap  = contextHolder.get();
        if (null != threadMap && threadMap.containsKey(key)){
            return threadMap.remove(key);
        }
        return  null;
    }

    public static boolean isExist(String key){
        return Optional.ofNullable(contextHolder.get()).filter(m->m.containsKey(key)).isPresent();
    }

    public static void clear(){
        Optional.ofNullable(contextHolder.get()).ifPresent(Map::clear);
    }

    public static Map<String,Object> getContextMap(){
        return contextHolder.get();
    }

    public static void setMap(Map<String,Object> contextMap){
        Optional.ofNullable(contextMap).filter(c->!c.isEmpty()).ifPresent(c->{
            Map<String,Object> threadMap  = contextHolder.get();
            if (null == threadMap){
                contextHolder.set(contextMap);
            }else {
                threadMap.putAll(contextMap);
            }
        });
    }
}
