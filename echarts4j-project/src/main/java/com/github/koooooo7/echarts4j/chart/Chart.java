package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.type.FuncStr;

public interface Chart<T> {

    ChartType getChartType();

    String getOptions();

    ChartOption getChartOptions();

    String getChartId();

    Chart<T> overlap(Chart<?> c);

    T addJSFunction(FuncStr funcStr);

    T addListener(String eventName, FuncStr handler);

    T addListener(String eventName, FuncStr query, FuncStr handler);

    void postProcessor(Canvas canvas);
}
