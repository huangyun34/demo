package com.my.admin;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LinShen on 2017/6/8.
 */


public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T readFromString(String json, Class<T> cls) {
        if(json == null || "".equals(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readFromBytes(byte[] bytes, Class<T> cls) {
        try {
            return mapper.readValue(bytes, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> readList(String json, Class<T> cls) {
        if(json == null || "".equals(json)) {
            return null;
        }
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, cls);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, Object> readMap(String json) {
        if(json == null || "".equals(json)) {
            return null;
        }
        JavaType javaType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertValue(Object from, Class<T> cls) {
        return mapper.convertValue(from, cls);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        InsertBean ds = (InsertBean)context.getBean("insertBean");
        System.out.println(ds);
    }
}
