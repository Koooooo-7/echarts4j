package com.github.koooooo7.echarts4j.tpl.spec.core;

import com.github.koooooo7.echarts4j.chart.BarChart;
import com.github.koooooo7.echarts4j.option.ChartOption;

public class BarChartFactory implements ChartFactory<BarChart> {
    @Override
    public BarChart getChart() {
        return BarChart.builder().options(ChartOption.builder().build()).build();
    }

    @Override
    public boolean support(String type) {
        return "bar".equalsIgnoreCase(type);
    }
}
