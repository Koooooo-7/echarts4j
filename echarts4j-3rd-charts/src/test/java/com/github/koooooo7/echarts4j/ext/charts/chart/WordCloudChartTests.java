package com.github.koooooo7.echarts4j.ext.charts.chart;

import com.github.koooooo7.echarts4j.ext.charts.Enhanced3rdChartsCanvas;
import com.github.koooooo7.echarts4j.ext.charts.series.WordCloudChartSeries;
import com.github.koooooo7.echarts4j.helper.DataHelper;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Title;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;


class WordCloudChartTests {

    @Test
    void shouldGenerateWordCloudChart_WhenCallTheChartConfig_GivenNecessaryConfigs() {
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
                .addCharts(WordCloudChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("WordCloud Basic").build())
                                .build()
                                .addSeries(WordCloudChartSeries.builder()
                                        .data(data).build())
                        ).build()).build()
                .render();

    }

}