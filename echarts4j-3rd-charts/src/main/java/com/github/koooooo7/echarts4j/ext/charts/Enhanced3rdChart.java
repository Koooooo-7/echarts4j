package com.github.koooooo7.echarts4j.ext.charts;

import com.github.koooooo7.echarts4j.chart.Canvas;

import java.util.function.Consumer;

public interface Enhanced3rdChart {

    String getType();

    Consumer<Canvas> canvasPostProcessor();

}
