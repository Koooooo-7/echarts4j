package com.github.koooooo7.echarts4j.tpl.spec.core;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.tpl.spec.Echarts4jC;
import com.github.koooooo7.echarts4j.util.JsonUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class Echarts4jDefListenerTests {

    @Test
    void testParser() throws IOException {
        final InputStream resource = getClass().getClassLoader().getResourceAsStream("data/test.e4j");
        final Canvas canvas = Echarts4jC.parse(resource);
        canvas.getCharts().values().forEach(c -> {
            System.out.println(JsonUtil.writeValueAsString(c.getChartOptions()));
        });
    }

}