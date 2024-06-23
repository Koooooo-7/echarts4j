package koy.github.koooooo7.echarts.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import koy.github.koooooo7.echarts.exception.ChartException;
import koy.github.koooooo7.echarts.type.FuncStrSerializer;
import koy.github.koooooo7.echarts.type.FuncStr;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    private static final JsonMapper jsonMapper;

    static {
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(FuncStr.class, new FuncStrSerializer());
        jsonMapper = JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, false)
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
