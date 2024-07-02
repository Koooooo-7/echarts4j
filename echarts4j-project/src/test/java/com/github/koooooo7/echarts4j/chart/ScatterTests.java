package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.series.ScatterChartSeriesOption;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ScatterTests {
    @Test
    void shouldGenerateScatterChart_WhenCallThePieChartConfig_GivenNecessaryConfigs() {
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<Integer> item = new ArrayList<>();
            item.add(ThreadLocalRandom.current().nextInt(100));
            item.add(ThreadLocalRandom.current().nextInt(100));
            data.add(item);
        }
        Canvas.builder()
                .addCharts(ScatterChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Scatter Chart").build())
                                .build()
                                .addSeries(ScatterChartSeriesOption.builder()
                                        .data(data).build()))
                        .build())
                .build()
                .render();

    }
}
