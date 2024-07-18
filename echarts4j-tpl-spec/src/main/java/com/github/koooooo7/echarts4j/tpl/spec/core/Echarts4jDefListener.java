package com.github.koooooo7.echarts4j.tpl.spec.core;

import com.github.koooooo7.echarts4j.chart.BaseChart;
import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.Chart;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jBaseListener;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jParser;
import com.github.koooooo7.echarts4j.tpl.spec.processor.OptionProcessor;
import com.github.koooooo7.echarts4j.tpl.spec.processor.ProcessorContext;

public class Echarts4jDefListener extends Echarts4jBaseListener {
    private Canvas currentWorkCanvas;
    private Chart<?> currentWorkChart;

    @Override
    public void enterCanvas(Echarts4jParser.CanvasContext ctx) {
        String currentWorkCanvasID = ctx.ID().getText();
        this.currentWorkCanvas = Canvas.builder().title(currentWorkCanvasID).build();
    }

    @Override
    public void enterChart(Echarts4jParser.ChartContext ctx) {
        final String chartType = ctx.type().getText();
        final String chartID = ctx.ID().getText();
        final Chart<?> chart = ChartFactoryManager.getChart(chartType);
        ((BaseChart<?>) chart).setChartId(chartID);
        this.currentWorkChart = chart;
        this.currentWorkCanvas.asBuilder().addCharts(chart).build();
        super.enterChart(ctx);
    }


    @Override
    public void enterOption(Echarts4jParser.OptionContext ctx) {
        OptionProcessor.getINSTANCE().apply(new ProcessorContext(currentWorkCanvas, currentWorkChart, ctx));
    }


    @Override
    public void exitCanvas(Echarts4jParser.CanvasContext ctx) {
        currentWorkCanvas.asBuilder().build();
        super.exitCanvas(ctx);
    }

    public Canvas get() {
        return currentWorkCanvas;
    }
}
