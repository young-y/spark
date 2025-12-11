/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.enricher;


import com.glory.http.client.service.wrapper.AuthentificationWrapper;
import com.glory.http.client.service.wrapper.HttpRequestWrapper; /**
 * @author : YY
 * @date : 2025/11/23
 * @descprition :
 *	provide token to http request,{@linkplain AuthentificationEnricher}
 */
@FunctionalInterface
public interface AuthorizationSupplier {

	<T,S>AuthentificationWrapper apply(HttpRequestWrapper<T,S> wrapper) ;


}
