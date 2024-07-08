package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.Toolbox;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.series.bar.BarChartSeries;
import com.github.koooooo7.echarts4j.option.series.bar.MarkLine;
import com.github.koooooo7.echarts4j.option.series.line.LineChartSeries;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BarChartTests {
    private final List<Object> data1 = new ArrayList<>();
    private final List<Object> data2 = new ArrayList<>();
    private final List<String> x = new ArrayList<>();

    @BeforeEach
    void setUp() {
        data1.clear();
        data2.clear();
        x.add("Mon");
        x.add("Tue");
        x.add("Wed");
        x.add("Thu");
        x.add("Fri");
        x.add("Sat");
        x.add("Sun");
        for (int i = 0; i < 20; i++) {
            data1.add(ThreadLocalRandom.current().nextInt(100));
            data2.add(ThreadLocalRandom.current().nextInt(100));
        }


    }

    @Test
    void shouldRenderCorrectly_WhenRenderTheLineCharts_GivenRelatedConfigs() {

        final String chartTitle = "My First echarts4j Bar";
        final String legendFormatter = "'Legend {name}'";
        final String seriesName = "Cole";
        final String seriesName2 = "Peps";

        final BarChart c = BarChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .legend(Legend.builder()
                                .formatter(FuncStr.of(legendFormatter)).build())
                        .toolbox(Toolbox.builder()
                                .showTitle(true)
                                .feature(Toolbox.Feature.builder()
                                        .saveAsImage(Toolbox.SaveAsImage.builder().build())
                                        .restore(Toolbox.Restore.builder().build())
                                        .dataView(Toolbox.DataView.builder().build())
                                        .build())
                                .build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(BarChartSeries.builder()
                                .name(seriesName)
                                .data(data1)
                                .markLine(MarkLine.builder()
                                        .data(Arrays.asList(MarkLine.MarkLineDataItem.builder()
                                                        .name("The Max")
                                                        .type("max")
                                                        .build(),
                                                MarkLine.MarkLineDataItem.builder()
                                                        .name("The Min")
                                                        .type("min")
                                                        .build()))
                                        .build())
                                .build())
                        .addSeries(LineChartSeries.builder()
                                .name(seriesName2)
                                .data(data2)
                                .build())
                )
                .build();


        try {
            Canvas.builder()
                    .addCharts(c)
                    .build()
                    .render();
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void shouldRenderCorrectly_WhenRenderThe2BarCharts_GivenRelatedConfigs() {

        final String chartTitle = "My First echarts4j Bar";
        final String legendFormatter = "'Top: {name}'";
        final String seriesName = "Cole";
        final String seriesName2 = "Peps";

        try {
            Canvas.builder()
                    .addCharts(BarChart.builder()
                            .options(ChartOption.builder()
                                    .title(Title.builder()
                                            .text(chartTitle).build())
                                    .legend(Legend.builder()
                                            .formatter(FuncStr.of(legendFormatter)).build())
                                    .toolbox(Toolbox.builder()
                                            .showTitle(true)
                                            .feature(Toolbox.Feature.builder()
                                                    .saveAsImage(Toolbox.SaveAsImage.builder().build())
                                                    .restore(Toolbox.Restore.builder().build())
                                                    .dataView(Toolbox.DataView.builder().build())
                                                    .build())
                                            .build())
                                    .xAxis(XAxis.builder()
                                            .data(x)
                                            .build())
                                    .yAxis(YAxis.builder().build())
                                    .build()
                                    .addSeries(BarChartSeries.builder()
                                            .name(seriesName)
                                            .data(data1)
                                            .markLine(MarkLine.builder()
                                                    .data(Arrays.asList(MarkLine.MarkLineDataItem.builder()
                                                                    .name("The Max")
                                                                    .type("max")
                                                                    .build(),
                                                            MarkLine.MarkLineDataItem.builder()
                                                                    .name("The Min")
                                                                    .type("min")
                                                                    .build()))
                                                    .build())
                                            .build())
                                    .addSeries(BarChartSeries.builder()
                                            .name(seriesName2)
                                            .data(data2)
                                            .build())
                            )
                            .build())
                    .build()
                    .renderTo(new File("bar.html"));
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
