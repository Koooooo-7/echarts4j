package com.github.koooooo7.echarts4j.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.koooooo7.echarts4j.exception.ChartException;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.type.FuncStrSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    private static final JsonMapper jsonMapper;

    static {
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(FuncStr.class, new FuncStrSerializer());
        jsonMapper = JsonMapper.builder()
                .addModule(simpleModule)
                .build();
    }

    public static String writeValueAsString(Object obj) {
        try {
            return jsonMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ChartException(e);
        }
    }
}
