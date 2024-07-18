package com.github.koooooo7.echarts4j.tpl.spec.processor;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.Chart;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jParser;

public class ProcessorContext {
    private final Canvas canvas;
    private final Chart<?> chart;
    private final Echarts4jParser.OptionContext optionContext;

    public ProcessorContext(Canvas canvas, Chart<?> chart, Echarts4jParser.OptionContext optionContext) {
        this.canvas = canvas;
        this.chart = chart;
        this.optionContext = optionContext;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Chart<?> getChart() {
        return chart;
    }

    public Echarts4jParser.OptionContext getOptionContext() {
        return optionContext;
    }
}
