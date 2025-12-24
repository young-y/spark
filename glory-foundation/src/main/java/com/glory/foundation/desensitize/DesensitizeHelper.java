/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.desensitize;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.glory.foundation.crypto.CryptoHelper;
import com.glory.foundation.crypto.CryptoContext;
import com.glory.foundation.desensitize.condition.DesensitizePredicate;
import com.glory.foundation.desensitize.jackson.DesensitizeAnnotationIntrospector;
import com.glory.foundation.desensitize.jackson.DesensitizePropertyFilter;
import com.glory.foundation.desensitize.rule.DesensitizeRule;
import com.glory.foundation.desensitize.rule.DesensitizeRuleConfig;
import com.glory.foundation.formater.ObjectFormater;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author : YY
 * @date : 2025/12/22
 * @descprition :
 *
 */
@Component
public class DesensitizeHelper {
	private static final String PATTEN_MASK_CRYPTO = "PattenMask";
	private static List<DesensitizeRule> ruleList ;
	private static ObjectMapper desensitizeMapper;
	private static DesensitizePredicate desensitizePredicate;

	public static Object desensitizeToJson(Object obj){
		if (Objects.nonNull(desensitizeMapper)&&Objects.nonNull(obj)){
			try {
				return desensitizeMapper.writeValueAsString(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return obj;
	}
	@SuppressWarnings("unchecked")
	public static String desensitize(CryptoContext context){
		if (activity()){
			String algorithm = context.getAlgorithm();
			if (!StringUtils.hasLength(algorithm) ||PATTEN_MASK_CRYPTO.equals(algorithm)){
				return CryptoHelper.getCrypto(PATTEN_MASK_CRYPTO).encrypt(context);
			}
			return CryptoHelper.encrypt(context.getValue(),algorithm,context.isWithPrefix());
		}
		return context.getValue();
	}

	public static boolean include(String name){
		return !activity() || null == ruleList || ruleList.stream().allMatch(r->r.include(name));
	}

	public static boolean match(Annotated annotated){
		if (activity()){
			Desensitize desensitize = annotated.getAnnotation(Desensitize.class);
			if (null != desensitize){
				return true;
			}
			AnnotatedElement element = annotated.getAnnotated();
			try{
				if (element instanceof Method){
					PropertyDescriptor property = BeanUtils.findPropertyForMethod((Method) element);
					if (null !=property){
						return match(property.getName());
					}
				}
			}catch (Exception ignored){
			}
		}
		return false;
	}

	public static boolean match(String name){
		return activity() &&null != ruleList&&ruleList.stream().anyMatch(r->r.include(name));
	}

	public static CryptoContext createContext(Annotated annotated){
		Desensitize desensitize = annotated.getAnnotation(Desensitize.class);
		if (null != desensitize){
			return CryptoContext.create(desensitize);
		}
		AnnotatedElement element = annotated.getAnnotated();
		try{
			if (element instanceof Method){
				PropertyDescriptor property = BeanUtils.findPropertyForMethod((Method) element);
				if (null !=property){
					DesensitizeRule rule = findRule(property.getName());
					if (null != rule){
						return rule.generate();
					}
				}
			}
		}catch (Exception ignored){
		}
		return null;
	}

	public static DesensitizeRule findRule(String name){
		if (CollectionUtils.isEmpty(ruleList)){
			return null;
		}
		return ruleList.stream().filter(r -> r.match(name)).findFirst().orElse(null);
	}

	public static void enroll(DesensitizeRule rule){
		Optional.ofNullable(rule).ifPresent(r->{
			ruleList.add(rule);
		});
	}

	public static void enroll(List<DesensitizeRule> rules){
		Optional.ofNullable(rules).ifPresent(rs->{
			DesensitizeHelper.ruleList.addAll(rs);
		});
	}

	private static boolean activity(){
		return null == desensitizePredicate || desensitizePredicate.test();
	}

	@Autowired
	public void setFormater(ObjectFormater formater) {
		ObjectMapper provide = formater.provide();
		desensitizeMapper = provide.copy();
		initDesensitize(desensitizeMapper);
	}
	@Autowired
	public void setRuleConfig(DesensitizeRuleConfig ruleConfig) {
		ruleList = ruleConfig.getRules();
	}

	@Autowired
	public  void setDesensitizePredicate(DesensitizePredicate desensitizePredicate) {
		DesensitizeHelper.desensitizePredicate = desensitizePredicate;
	}

	private static void initDesensitize(ObjectMapper mapper){
		SimpleBeanPropertyFilter filter = new DesensitizePropertyFilter();
		mapper.setAnnotationIntrospector(new DesensitizeAnnotationIntrospector());
		Map<Class<?>,Class<?>> mixins = new HashMap<>(8);
//        mixins.put(Map.class, FilterConfiguration.MapPropertyFilterView.class);
		mixins.put(Object.class,DesensitizeFilterView.class);
		mapper.setMixIns(mixins);
		mapper.setFilterProvider(new SimpleFilterProvider()
				.addFilter("DESENSITIZE_FILTER_NAME",filter));
	}

	@JsonFilter("DESENSITIZE_FILTER_NAME")
	interface DesensitizeFilterView{}
}
