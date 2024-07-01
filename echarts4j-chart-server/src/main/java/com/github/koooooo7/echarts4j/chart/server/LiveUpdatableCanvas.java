package com.github.koooooo7.echarts4j.chart.server;


import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.Chart;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface LiveUpdatableCanvas {

    default <T extends Chart<T>> LiveUpdatableCanvas liveUpdateChartModifier(String chartId, Consumer<Optional<Chart<T>>> modifier) {
        getTarget().asBuilder().updateChart(chartId, modifier).build();
        return this;
    }

    default <T extends Chart<T>> LiveUpdatableCanvas liveUpdateChartsModifier(BiConsumer<String, Chart<?>> chartsModifier) {
        getTarget().asBuilder().updateCharts(chartsModifier).build();
        return this;
    }


    Canvas getTarget();

    default void emit() {
    }

}
