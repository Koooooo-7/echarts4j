package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;

public interface Chart {

    ChartType getChartType();

    String getOptions();

    ChartOption getChartOptions();

    String getChartId();

    Chart overlap(Chart c);

    void postProcessor();
}
