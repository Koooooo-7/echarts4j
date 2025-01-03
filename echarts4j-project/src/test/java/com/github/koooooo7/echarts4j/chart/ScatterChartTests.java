package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.Toolbox;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.series.effectscatter.EffectScatterSeries;
import com.github.koooooo7.echarts4j.option.series.scatter.ScatterChartSeries;
import com.github.koooooo7.echarts4j.render.Render;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ScatterChartTests {
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
    void shouldRenderCorrectly_WhenRenderTheScatterCharts_GivenRelatedConfigs() throws IOException {

        final String chartTitle = "My Scatter Charts";
        final String legendFormatter = "'Legend {name}'";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";

        final ScatterChart c = ScatterChart.builder()
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
                        .addSeries(ScatterChartSeries.builder()
                                .name(seriesName)
                                .data(data1)
                                .build())
                        .addSeries(ScatterChartSeries.builder()
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
//            render.render(cvs, new FileWriter("scatter.html"));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void shouldRenderCorrectly_WhenRenderTheEffectScatterCharts_GivenRelatedConfigs() throws IOException {

        final String chartTitle = "My Effect Scatter Charts";
        final String legendFormatter = "'Legend {name}'";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";

        final EffectScatterChart c = EffectScatterChart.builder()
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
                        .addSeries(EffectScatterSeries.builder()
                                .name(seriesName)
                                .data(data1)
                                .build())
                        .addSeries(EffectScatterSeries.builder()
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
            render.render(cvs, new FileWriter("effect-scatter.html"));
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
