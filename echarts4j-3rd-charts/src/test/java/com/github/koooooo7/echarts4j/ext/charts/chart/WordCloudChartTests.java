package com.github.koooooo7.echarts4j.ext.charts.chart;

import com.github.koooooo7.echarts4j.chart.LineChart;
import com.github.koooooo7.echarts4j.ext.charts.Enhanced3rdChartsCanvas;
import com.github.koooooo7.echarts4j.ext.charts.series.WordCloudChartSeries;
import com.github.koooooo7.echarts4j.helper.DataHelper;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Title;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


class WordCloudChartTests {

    @Test
    void shouldGenerateWordCloudChart_WhenCallTheChartConfig_GivenNecessaryConfigs() {
        final DataHelper helper = DataHelper.create()
                .addValueField(Integer.class)
                .addNameField().build()
                .addData(1048, "Search Engine")
                .addData(735, "Direct")
                .addData(580, "Email")
                .addData(484, "Union Ads")
                .addData(300, "Video Ads");
        for (int i = 0; i < 100; i++) {
            helper.addData(ThreadLocalRandom.current().nextInt(100), i + "_Placeholder");
        }

        Enhanced3rdChartsCanvas.builder()
                .addCharts(WordCloudChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("WordCloud Basic").build())
                                .build()
                                .addSeries(WordCloudChartSeries.builder()
                                        .data(helper.get()).build())
                        ).build()).build()
                .render();

    }

    @Test
    void shouldThrowUnSupportEx_WhenCallTheChartConfig_GivenOverlap() {
        final DataHelper helper = DataHelper.create()
                .addValueField(Integer.class)
                .addNameField().build()
                .addData(1048, "Search Engine")
                .addData(735, "Direct")
                .addData(580, "Email")
                .addData(484, "Union Ads")
                .addData(300, "Video Ads");
        for (int i = 0; i < 100; i++) {
            helper.addData(ThreadLocalRandom.current().nextInt(100), i + "_Placeholder");
        }

        try {
            Enhanced3rdChartsCanvas.builder()
                    .addCharts(WordCloudChart.builder()
                            .options(ChartOption.builder()
                                    .title(Title.builder().text("WordCloud Basic").build())
                                    .build()
                                    .addSeries(WordCloudChartSeries.builder()
                                            .data(helper.get()).build())
                            ).build().overlap(LineChart.builder().build())).build()
                    .render();

        } catch (UnsupportedOperationException ex) {
            Assertions.assertTrue(true);
        } catch (Exception ex) {
            Assertions.fail();
        }


    }

}