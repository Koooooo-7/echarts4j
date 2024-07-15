package com.github.koooooo7.echarts4j.tpl.spec.core;

import com.github.koooooo7.echarts4j.chart.BaseChart;
import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.Chart;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.Toolbox;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jBaseListener;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jParser;

import java.util.Objects;

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
    public void enterName(Echarts4jParser.NameContext ctx) {
        super.enterName(ctx);
    }

    @Override
    public void enterTitle(Echarts4jParser.TitleContext ctx) {
        this.currentWorkChart.getChartOptions().setTitle(Title.builder().text(ctx.ID().getText()).build());
    }

    @Override
    public void enterToolbox(Echarts4jParser.ToolboxContext ctx) {
        Toolbox toolbox = this.currentWorkChart.getChartOptions().getToolbox();
        if (Objects.isNull(toolbox)) {
            toolbox = Toolbox.builder().build();
        }

        Toolbox.Feature feature = toolbox.getFeature();
        if (Objects.isNull(feature)) {
            feature = Toolbox.Feature.builder().build();
        }

        for (Echarts4jParser.FeatureContext feat : ctx.feature()) {

            final String text = feat.getText();
            if ("dataView".equalsIgnoreCase(text)) {
                feature.setDataView(Toolbox.DataView.builder().build());
            }

            if ("saveAsImage".equalsIgnoreCase(text)) {
                feature.setSaveAsImage(Toolbox.SaveAsImage.builder().build());
            }

            if ("restore".equalsIgnoreCase(text)) {
                feature.setRestore(Toolbox.Restore.builder().build());
            }

        }

        toolbox.setFeature(feature);
        this.currentWorkChart.getChartOptions().setToolbox(toolbox);
    }

    @Override
    public void exitChart(Echarts4jParser.ChartContext ctx) {
        super.exitChart(ctx);
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
