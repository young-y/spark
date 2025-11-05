/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.app;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */

public class SparkTest {

    public static void main(String[] args) {
        threadLocalTest();
    }

    private static void threadLocalTest(){
        ThreadLocal<Map<String,Object>> mapContext = new ThreadLocal<>();
        Map<String,Object> map = new HashMap<>();
        map.put("aa","this is a");
        mapContext.set(map);
        boolean hasKey =Optional.ofNullable(mapContext.get()).filter(m->m.containsKey("aaa")).isPresent();
        System.out.println(String.format(">> Test key exist %s ",hasKey));
    }
}
