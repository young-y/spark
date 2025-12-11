/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */

public interface UniquePropertiesAware {
    @JsonIgnore
    default Set<String> getUniqueProperties() {
        return Set.of();
    }
}
