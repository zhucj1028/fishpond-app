package com.fishpond.fishpondapp.codegen.core;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BeanUtil {
    /**
     * 获取对象属性map集
     */
    public static Map<String, Object> asMap(Object object) {
        BeanWrapper bw = new BeanWrapperImpl(object);
        Map<String, Object> map = new HashMap<>();
        for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
            if (!"class".equals(pd.getName())) {
                map.put(pd.getName(), bw.getPropertyValue(pd.getName()));
            }
        }
        return map;
    }

    /**
     * 将对象属性值设置为属性名与Map键对应的值
     */
    public static void populate(Object object, Map<String, ?> map) {
        BeanWrapper bw = new BeanWrapperImpl(object);
        bw.setPropertyValues(new MutablePropertyValues(map), true, true);
    }

    /**
     * 反射创建类实例对象，并将对象属性值设置为属性名与Map键对应的值
     */
    public static <T> T populate(Class<T> type, Map<String, ?> map) {
        T t = null;
        if (map != null) {
            t = BeanUtils.instantiateClass(type);
            populate(t, map);
        }
        return t;
    }

    public static <T> List<T> populateListAs(List<Map<String, Object>> froms, Class<T> type) {
        List<T> result = new ArrayList<>();
        if (froms != null) {
            for (Map<String, Object> from : froms) {
                result.add(populate(type, from));
            }
        }
        return result;
    }

    /**
     * 将源对象from的属性值复制到目标对象to中
     *
     * @param from 源对象
     * @param to   目标对象
     */
    public static void copy(Object from, Object to) {
        BeanUtils.copyProperties(from, to);
    }

    /**
     * 将源对象from的属性值复制到目标类实例中
     */
    @SneakyThrows
    public static <T> T copyAs(Object from, Class<T> type) {
        T to = null;
        if (from != null) {
            to = type.newInstance();
            BeanUtil.copy(from, to);
        }
        return to;
    }

    /**
     * 将源集合中对象的属性值复制到目标类实例集合中
     */
    public static <T> List<T> copyListAs(List<?> froms, Class<T> type) {
        List<T> result = new ArrayList<>();
        if (froms != null) {
            for (Object from : froms) {
                result.add(copyAs(from, type));
            }
        }
        return result;
    }
}
