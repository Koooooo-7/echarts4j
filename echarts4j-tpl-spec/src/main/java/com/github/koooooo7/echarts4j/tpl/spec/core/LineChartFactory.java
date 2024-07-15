package com.github.koooooo7.echarts4j.tpl.spec.core;

import com.github.koooooo7.echarts4j.chart.LineChart;
import com.github.koooooo7.echarts4j.option.ChartOption;

public class LineChartFactory implements ChartFactory<LineChart> {
    @Override
    public LineChart getChart() {
        return LineChart.builder().options(ChartOption.builder().build()).build();
    }

    @Override
    public boolean support(String type) {
        return "line".equalsIgnoreCase(type);
    }
}
