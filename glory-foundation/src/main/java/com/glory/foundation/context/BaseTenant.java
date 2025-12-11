/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.context;


import com.glory.foundation.domain.PropertyDesc;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/11/8
 * @descprition :
 *
 */

public class BaseTenant implements PropertyDesc {
    private String code;
    private String name;
    private ZoneId zoneId = ZoneId.systemDefault();
    private final Map<String, Object> properties = new HashMap<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public Object getValue(String key) {
        return properties.get(key);
    }
}
