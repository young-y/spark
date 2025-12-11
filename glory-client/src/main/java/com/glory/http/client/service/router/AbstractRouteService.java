/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.router;


import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author : YY
 * @date : 2025/12/4
 * @descprition :
 *
 */

public abstract class AbstractRouteService<S> implements RouteService<S>{

	protected List<S> services;

	public S select(@Nonnull String targetAppName) {
		if (isLocalRouter(targetAppName)) {
			return services.stream().filter(localPredicate()).findFirst().orElseThrow();
		}
		return services.stream().filter(remotePredicate()).findFirst().orElseThrow();
	}

	protected abstract boolean isLocalRouter(String targetAppName);

	protected Predicate<S> localPredicate() {
		return s -> s instanceof LocalService;
	}

	protected Predicate<S> remotePredicate() {
		return s -> s instanceof RemoteService;
	}

	@Autowired
	public void setServices(List<S> services) {
		this.services = services;
	}

}
