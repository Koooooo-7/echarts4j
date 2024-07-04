package com.github.koooooo7.echarts4j.ext.charts.chart;

import com.github.koooooo7.echarts4j.chart.BaseChart;
import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.Chart;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.ext.charts.Enhanced3rdChart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Consumer;

/**
 * The WordCloud support from {@link   <a href="https://github.com/ecomfe/echarts-liquidfill"></a>}
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LiquidFillChart extends BaseChart<LiquidFillChart> implements Enhanced3rdChart {
    private static final String LIQUID_ADDITION_ASSET =
            "https://cdn.jsdelivr.net/gh/ecomfe/echarts-liquidfill@master/dist/echarts-liquidfill.min.js";

    @Override
    public ChartType getChartType() {
        return ChartType.Generic;
    }

    @Override
    public String getType() {
        return Chart3rdType.LiquidFill.getType();
    }

    @Override
    public Consumer<Canvas> canvasPostProcessor() {
        return canvas -> canvas.asBuilder()
                .appendJSAssets(LIQUID_ADDITION_ASSET)
                .build();
    }

    @Override
    public Chart<LiquidFillChart> overlap(Chart<?> c) {
        throw new UnsupportedOperationException("Liquid chart currently does not support overlap");
    }
}
