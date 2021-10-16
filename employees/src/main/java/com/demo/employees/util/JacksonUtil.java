package com.demo.employees.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

    static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static Object convertMapToObject(Object objectMap, Class objectClass) {
        return mapper.convertValue(objectMap, objectClass);
    }
}
