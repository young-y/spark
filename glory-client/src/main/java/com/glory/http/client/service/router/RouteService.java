/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.router;


import jakarta.annotation.Nonnull;

/**
 * @author : YY
 * @date : 2025/12/8
 * @descprition :
 *
 */

public interface RouteService<S> {

	default S select(){
		return select(targetAppName());
	}

	S select(@Nonnull String name);

	String targetAppName();
}
