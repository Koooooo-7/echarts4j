package com.github.koooooo7.echarts.chart;

import com.github.koooooo7.echarts.option.ChartOption;

public interface Chart {

    ChartType getChartType();

    String getOptions();

    ChartOption getChartOptions();

    String getChartId();

    Chart overlap(Chart c);

    void postProcessor();
}
