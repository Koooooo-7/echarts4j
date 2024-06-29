package com.github.koooooo7.echarts4j.snapshot.playwright;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.LineChart;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.series.BarChartSeriesOption;
import com.github.koooooo7.echarts4j.option.series.ListChartSeriesOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class SnapshotRenderTest {
    private static final List<Object> data1 = new ArrayList<>();
    private static final List<Object> data2 = new ArrayList<>();
    private static final List<String> x = new ArrayList<>();

    @BeforeEach
    void setup() {
        data1.clear();
        data2.clear();
        x.clear();
        for (int i = 0; i < 20; i++) {
            data1.add(ThreadLocalRandom.current().nextInt(100));
            data2.add(ThreadLocalRandom.current().nextInt(100));
            x.add(ThreadLocalRandom.current().nextInt(100) + "xxx");
        }

    }

    @Test
    void shouldGeneratePNG_WhenCallTheRender_GivenSnapshotRender() {
        final String chartTitle = "My Overlap Charts";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";
        final LineChart c = LineChart.builder()
                .options(ChartOption.builder()
                        .animation(false)
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .legend(Legend.builder().build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(ListChartSeriesOption.builder()
                                .name(seriesName)
                                .data(data1)
                                .build())
                        .addSeries(BarChartSeriesOption.builder()
                                .name(seriesName2)
                                .data(data2)
                                .build())
                )
                .build();

        File target = new File("test1.jpg");
        try {
            Canvas.builder()
                    .addCharts(c)
                    .build()
                    .renderTo(target);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertTrue(target.exists());

    }

}