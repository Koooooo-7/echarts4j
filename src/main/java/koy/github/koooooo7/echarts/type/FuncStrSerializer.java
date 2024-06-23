package koy.github.koooooo7.echarts.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class FuncStrSerializer extends JsonSerializer<FuncStr> {
    private static final String PREFIX_FOR_RAW_FUNC_STR = ":";

    @Override
    public void serialize(FuncStr value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeRaw(PREFIX_FOR_RAW_FUNC_STR + value.getVal());
    }

}
