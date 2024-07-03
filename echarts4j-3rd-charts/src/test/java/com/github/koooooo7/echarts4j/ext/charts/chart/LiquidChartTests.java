package com.github.koooooo7.echarts4j.ext.charts.chart;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.PieChart;
import com.github.koooooo7.echarts4j.ext.charts.Enhanced3rdChartsCanvas;
import com.github.koooooo7.echarts4j.ext.charts.series.LiquidChartSeries;
import com.github.koooooo7.echarts4j.helper.DataHelper;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.series.PieChartSeriesOption;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

class LiquidChartTests {

    @Test
    void shouldGenerateLiquidChart_WhenCallTheLiquidChartConfig_GivenNecessaryConfigs() {
        final List<LinkedHashMap<String, Object>> data = DataHelper.create()
                .addValueField(Integer.class)
                .addNameField().build()
                .addData(1048, "Search Engine")
                .addData(735, "Direct")
                .addData(580, "Email")
                .addData(484, "Union Ads")
                .addData(300, "Video Ads")
                .get();

        Enhanced3rdChartsCanvas.builder()
                .addCharts(LiquidChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Liquid Basic").build())
                                .build()
                                .addSeries(LiquidChartSeries.builder()
                                        .data(Arrays.asList("0.6", "0.5", "0.1")).build())
                        ).build())
                .addCharts(PieChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Pie Chart").build())
                                .build()
                                .addSeries(PieChartSeriesOption.builder()
                                        .data(data).build()))
                        .build()).build()
                .render();

    }

    @Test
    void shouldGenerateLiquidChart_WhenCallTheLiquidChartConfig_GivenNecessaryConfigsWithPresetCanvas() {

        final List<LinkedHashMap<String, Object>> data = DataHelper.create()
                .addValueField(Integer.class)
                .addNameField().build()
                .addData(1048, "Search Engine")
                .addData(735, "Direct")
                .addData(580, "Email")
                .addData(484, "Union Ads")
                .addData(300, "Video Ads")
                .get();
        // preset a chart in standard canvas
        final Canvas canvasExistPieChart = Canvas.builder()
                .layout(Canvas.CanvasLayout.NONE)
                .addCharts(PieChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Pie Chart").build())
                                .build()
                                .addSeries(PieChartSeriesOption.builder()
                                        .data(data).build()))
                        .build())
                .build();

        // box to 3rd support
        Enhanced3rdChartsCanvas.box3rdChartsSupport(canvasExistPieChart.asBuilder())
                .addCharts(LiquidChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Liquid Basic").build())
                                .build()
                                .addSeries(LiquidChartSeries.builder()
                                        .data(Arrays.asList("0.6", "0.5", "0.1")).build())
                        ).build())
                .build()
                .render();

    }

}