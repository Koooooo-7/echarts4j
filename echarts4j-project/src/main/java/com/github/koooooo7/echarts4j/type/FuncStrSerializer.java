package com.github.koooooo7.echarts4j.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class FuncStrSerializer extends JsonSerializer<FuncStr> {

    @Override
    public void serialize(FuncStr value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeRawValue(value.getVal());
    }

}
