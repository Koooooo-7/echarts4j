package com.github.koooooo7.echarts4j.tpl.spec.core;

import com.github.koooooo7.echarts4j.chart.Chart;

public interface ChartFactory<T extends Chart<?>> {


    T getChart();

    boolean support(String type);
}
