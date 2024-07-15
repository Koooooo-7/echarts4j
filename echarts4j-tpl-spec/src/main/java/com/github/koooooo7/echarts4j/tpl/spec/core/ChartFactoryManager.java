package com.github.koooooo7.echarts4j.tpl.spec.core;

import com.github.koooooo7.echarts4j.chart.Chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChartFactoryManager {

    private ChartFactoryManager() {
    }

    private static final List<ChartFactory<?>> factories = new ArrayList<>();

    static {
        factories.add(new LineChartFactory());
        factories.add(new BarChartFactory());
    }

    @SuppressWarnings("unchecked")
    public static <T extends Chart<?>> T getChart(String type) {
        return (T) Objects.requireNonNull(factories.stream().filter(f -> f.support(type)).findFirst().orElse(null)).getChart();
    }


}
