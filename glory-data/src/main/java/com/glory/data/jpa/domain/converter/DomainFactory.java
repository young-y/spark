/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/12/9
 * @descprition :
 *
 */
@Component
@SuppressWarnings({"rawtypes","unchecked"})
public class DomainFactory {
	private final static Map<String, DomainConverter> converterMap = new HashMap<>();
	private final static Map<String, DomainConverter> fromConverterMap = new HashMap<>();

	public static<T> T convert(Object value,Class<T> clz){
		return (T) findModelConverter(value.getClass(),clz).convert(value);
	}

	public static <T> T convert(Object source){
		return (T) findModelConverter(source.getClass()).convert(source);
	}

	private static DomainConverter findModelConverter(Type from){
		return fromConverterMap.get(from.getTypeName());
	}

	private static DomainConverter findModelConverter(Type from, Type to){
		return converterMap.get(pairKey(from,to));
	}

	@Autowired
	public void setModelConverters(List<DomainConverter> converters){
		Optional.ofNullable(converters).ifPresent(cs->{
			cs.forEach(c->{
				Type genericInterface = c.getClass().getGenericInterfaces()[0];
				if (genericInterface instanceof ParameterizedType){
					Type[] types = ((ParameterizedType)genericInterface).getActualTypeArguments();
					if (types.length == 2){
						register(types[0],types[1],c);
					}
				}
			});
		});
	}

	private static String pairKey(Type clzS,Type claT){
		return clzS.getTypeName()+"_TO_"+claT.getTypeName();
	}

	public static void register(Type clzS,Type claT,DomainConverter converter){
		converterMap.put(pairKey(clzS,claT),converter);
		fromConverterMap.put(clzS.getTypeName(),converter);
	}
}
