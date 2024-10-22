package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.helper.DataHelper;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.Toolbox;
import com.github.koooooo7.echarts4j.option.chart.Tooltip;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.series.line.LineChartSeries;
import com.github.koooooo7.echarts4j.option.series.line.MarkLine;
import com.github.koooooo7.echarts4j.option.series.line.MarkPoint;
import com.github.koooooo7.echarts4j.render.Render;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LineChartTests {
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
    void shouldRenderCorrectly_WhenRenderTheLineCharts_GivenRelatedConfigs() throws IOException {

        final String chartTitle = "My Charts";
        final String legendFormatter = "'Legend {name}'";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";

        final LineChart c = LineChart.builder()
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
                        .addSeries(LineChartSeries.builder()
                                .name(seriesName)
                                .data(data1)
                                .build())
                        .addSeries(LineChartSeries.builder()
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
//            render.render(cvs, new FileWriter("line.html"));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void shouldRenderCorrectly_WhenRenderTheLineCharts_GivenRelatedConfigsForSample_Temperature_Change_in_the_Coming_Week() {

        final LineChart lineChart = LineChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder().text("Temperature Change in the Coming Week").build())
                        .tooltip(Tooltip.builder().trigger("axis").build())
                        .legend(Legend.builder().build())
                        .toolbox(Toolbox.builder()
                                .show(true)
                                .feature(Toolbox.Feature.builder()
                                        .dataView(Toolbox.DataView.builder().readOnly(true).build())
                                        .restore(Toolbox.Restore.builder().build())
                                        .saveAsImage(Toolbox.SaveAsImage.builder().build())
                                        .build())
                                .build())
                        .xAxis(XAxis.builder()
                                .type("category")
                                .boundaryGap(FuncStr.of(false))
                                .data(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"))
                                .build())
                        .yAxis(YAxis.builder()
                                .type("value")
                                .axisLabel(YAxis.AxisLabel.builder()
                                        .formatter(FuncStr.ofStr("{value} °C"))
                                        .build())
                                .build())
                        .build()
                        // first series
                        .addSeries(LineChartSeries.builder()
                                .name("Highest")
                                .type("line") // it will be auto config if not set since we use the LineChartSeries
                                .data(Arrays.asList(10, 11, 13, 11, 12, 12, 9))
                                .markPoint(MarkPoint.builder()
                                        .data(Arrays.asList(
                                                // use the builtin pojo
                                                MarkPoint.MarkPointDataItem.builder()
                                                        .type("max")
                                                        .name("Max")
                                                        .build()
                                                // use the data helper for custom Map is also fine
                                                , DataHelper.create()
                                                        .addField("type", String.class)
                                                        .addNameField()
                                                        .build()
                                                        .addData("min", "Min")
                                                        .get().get(0)
                                        ))
                                        .build())
                                .markLine(MarkLine.builder()
                                        .data(DataHelper.create()
                                                .addField("type", String.class)
                                                .addNameField()
                                                .build()
                                                .addData("average", "Avg")
                                                .get())
                                        .build())
                                .build())
                        // second series
                        .addSeries(LineChartSeries.builder()
                                .name("Lowest")
                                .data(Arrays.asList(1, -2, 2, 5, 3, 2, 0))
                                .markPoint(
                                        MarkPoint.builder()
                                                .data(DataHelper.create()
                                                        .addNameField()
                                                        .addValueField(Integer.class)
                                                        .addField("xAxis", Integer.class)
                                                        .addField("yAxis", Double.class)
                                                        .build()
                                                        .addData("周最低", -2, 1, -1.5)
                                                        .get())
                                                .build()
                                )
                                .markLine(MarkLine.builder()
                                        .data(DataHelper.create()
                                                .addField("type", String.class)
                                                .addNameField()
                                                .build()
                                                .addData("average", "Avg")
                                                .get()
                                        )
                                        .build())
                                .build())
                )
                .build();

        Canvas.builder()
                .addCharts(lineChart)
                .build()
                .renderTo(new File("Temperature-Change-in-the-Coming-Week.html"));


        final String JsFuncStr = "console.log('echarts4j!');";
        final String JsFuncStrWithInstanceInjection = "console.log(%MY_ECHARTS%.getOption());";
        Canvas.builder()
                .addCharts(
                        LineChart.builder()
                                .options(ChartOption.builder().build())
                                .build()
                                .addJSFunction(FuncStr.of(JsFuncStr))
                                .addJSFunction(FuncStr.of(JsFuncStrWithInstanceInjection))

                )
                .build()
                .renderTo(new File("js.html"));

    }
}
