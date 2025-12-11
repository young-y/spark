/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.foundation.formater.FormatHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : YY
 * @date : 2025/10/31
 * @descprition :
 *
 */

public interface PropertyDesc {

    @JsonIgnore
    Object getValue(String key);

    @JsonIgnore
    default String getString(String key,String dv){
        Object o = getValue(key);
        if (o instanceof String){
            return o.toString();
        }
        return dv;
    }

    @JsonIgnore
    default Byte getByte(String key,Byte dv){
        Object o = getValue(key);
        if (o instanceof Byte){
            return (Byte) o;
        }else if (o instanceof String){
            return Byte.valueOf(o.toString());
        }
        return dv;
    }

    @JsonIgnore
    default Short getShort(String key,Short dv){
        Object o = getValue(key);
        if (o instanceof Short){
            return (Short) o;
        }else if (o instanceof String){
            return Short.valueOf(o.toString());
        }
        return dv;
    }

    @JsonIgnore
    default Integer getInteger(String key,Integer dv){
        Object o = getValue(key);
        if (o instanceof Integer){
            return (Integer) o;
        }else if (o instanceof String){
            return Integer.valueOf(o.toString());
        }
        return dv;
    }

    @JsonIgnore
    default Long getLong(String key,Long dv){
        Object o = getValue(key);
        if (o instanceof Long){
            return (Long) o;
        }else if (o instanceof String){
            return Long.valueOf(o.toString());
        }
        return dv;
    }

    @JsonIgnore
    default Double getDouble(String key,Double dv){
        Object o = getValue(key);
        if (o instanceof String){
            return Double.parseDouble(o.toString());
        }else if (o instanceof Double){
            return (Double) o;
        }
        return dv;
    }

    @JsonIgnore
    default Float getFloat(String key,Float dv){
        Object o = getValue(key);
        if (o instanceof String){
            return Float.parseFloat(o.toString());
        }else if (o instanceof Float){
            return (Float) o;
        }
        return dv;
    }

    @JsonIgnore
    default Boolean getBoolean(String key,Boolean dv){
        Object o = getValue(key);
        if (o instanceof String){
            String str = o.toString();
            if ("Y".equalsIgnoreCase(str) || "Yes".equalsIgnoreCase(str)){
                return true;
            }else if ("N".equalsIgnoreCase(str) || "No".equalsIgnoreCase(str)){
                return false;
            }
        }else if (o instanceof Boolean){
            return Boolean.getBoolean(o.toString());
        }
        return dv;
    }

    @JsonIgnore
    default Date getDate(String key, String dateFormat){
        Object o = getValue(key);
        if (o instanceof  Date){
            return (Date) o;
        }else if (o instanceof String){
            return FormatHelper.parse(o.toString(),dateFormat);
        }
        return null;
    }

    @JsonIgnore
    default LocalDate getLocalDate(String key, String dateFormat){
        Object o = getValue(key);
        if (o instanceof LocalDate){
            return (LocalDate) o;
        }else if (o instanceof  String ){
            return FormatHelper.parseLocalDate(o.toString(),dateFormat);
        }
        return null;
    }

    @JsonIgnore
    default LocalDateTime getLocalDateTime(String key, String format){
        Object o = getValue(key);
        if (o instanceof LocalDateTime){
            return (LocalDateTime) o;
        }else if(o instanceof String ){
            return FormatHelper.parseLocal(o.toString(), format);
        }
        return null;
    }

}
