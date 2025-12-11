/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.entity;


import com.glory.data.jpa.domain.PrimaryPropertySupport;
import com.glory.data.jpa.domain.PropertiesSyncAware;
import com.glory.data.jpa.domain.UniquePropertiesAware;

import java.io.Serializable;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */

public interface DomainAware extends Serializable, PrimaryPropertySupport, PropertiesSyncAware, UniquePropertiesAware {
}
