/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.context;


import com.glory.foundation.domain.PropertyDesc;

import java.util.HashMap;
import java.util.Map; /**
 * @author : YY
 * @date : 2025/11/8
 * @descprition :
 *
 */

public class BaseUser implements PropertyDesc {
    private Long userId;
    private String name;
    private BaseTenant baseTenant;
    private final Map<String,Object> properties = new HashMap<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseTenant getTenant() {
        return baseTenant;
    }

    public void setTenant(BaseTenant baseTenant) {
        this.baseTenant = baseTenant;
    }

    @Override
    public Object getValue(String key) {
        return properties.get(key);
    }
}
