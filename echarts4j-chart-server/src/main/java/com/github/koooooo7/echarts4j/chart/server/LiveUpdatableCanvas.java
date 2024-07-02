package com.github.koooooo7.echarts4j.chart.server;


import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.Chart;
import com.github.koooooo7.echarts4j.exception.RenderException;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@FunctionalInterface
public interface LiveUpdatableCanvas {

    default LiveUpdatableCanvas liveUpdateChart(String chartId, Consumer<Optional<Chart<?>>> modifier) {
        getTarget().asBuilder().updateChart(chartId, modifier).build();
        return this;
    }

    default LiveUpdatableCanvas liveUpdateChartScheduling(String chartId,
                                                          Consumer<Optional<Chart<?>>> modifier,
                                                          long initialDelay,
                                                          long period,
                                                          TimeUnit unit) {
        LiveUpdateScheduler.register(() -> {
            try {
                getTarget().asBuilder().updateChart(chartId, modifier).build();
                emit();
            } catch (Exception ex) {
                throw new RenderException(ex);
            }
        }, initialDelay, period, unit);
        return this;
    }


    Canvas getTarget();

    default void emit() {
    }

}
