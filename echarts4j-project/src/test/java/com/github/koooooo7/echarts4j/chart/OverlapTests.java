package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.series.BarChartSeries;
import com.github.koooooo7.echarts4j.option.series.LineChartSeries;
import com.github.koooooo7.echarts4j.render.Render;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OverlapTests {

    private final List<Object> data1 = new ArrayList<>();
    private final List<Object> data2 = new ArrayList<>();
    private final List<String> x = new ArrayList<>();

    @BeforeEach
    void setUp() {
        data1.clear();
        data2.clear();
        x.clear();
        for (int i = 0; i < 20; i++) {
            data1.add(ThreadLocalRandom.current().nextInt(100));
            data2.add(ThreadLocalRandom.current().nextInt(100));
            x.add(RandomStringUtils.random(3, true, false));
        }

    }

    @Test
    void showRenderOverlapChart_WhenCallTheOverlapViaSeries_GivenCorrectConfigs() {
        final String chartTitle = "My Overlap Charts";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";
        final LineChart c = LineChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .legend(Legend.builder().build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(LineChartSeries.builder()
                                .name(seriesName)
                                .data(data1)
                                .build())
                        .addSeries(BarChartSeries.builder()
                                .name(seriesName2)
                                .data(data2)
                                .build())
                )
                .build();

        try (StringWriter writer = new StringWriter()) {
            final Canvas cvs = Canvas.builder()
                    .addCharts(c)
                    .build();
            final Render render = RenderProvider.get();
            render.render(cvs, writer);
//            render.render(cvs, new FileWriter("overlap.html"));
        } catch (Exception e) {
            Assertions.fail();
        }

    }

    @Test
    void showRenderOverlapChart_WhenCallTheOverlapViaChartOverlapMethod_GivenCorrectConfigs() {
        final String chartTitle = "My Overlap Charts";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";
        final BarChart bar = BarChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .legend(Legend.builder().build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(BarChartSeries.builder()
                                .name(seriesName)
                                .data(data1)
                                .build())
                )
                .build();
        final LineChart line = LineChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(LineChartSeries.builder()
                                .name(seriesName2)
                                .data(data1)
                                .build())
                )
                .build();

        try (StringWriter writer = new StringWriter()) {
            final Canvas cvs = Canvas.builder()
                    .addCharts(bar.overlap(line))
                    .build();
            final Render render = RenderProvider.get();
            render.render(cvs, writer);
//            render.render(cvs, new FileWriter("overlap2.html"));
        } catch (Exception e) {
            Assertions.fail();
        }

    }
}
