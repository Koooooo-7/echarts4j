package com.github.koooooo7.echarts4j.canvas;


import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.GenericChart;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.series.GenericSeriesOption;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.jayway.jsonpath.JsonPath;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.render.Render;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CanvasTests {
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
    void shouldRenderCorrect_WhenCallTheCanvas_GivenBasicConfig() throws IOException {

        final String defaultTitle = "echarts4j";
        final String title = "My Canvas";
        final String mockToReplaceDefaultEchartsResources = "./mock/echarts.mock.js";
        final String customJs = "/mock.js";
        final String customCss = "/mock.css";
        try (StringWriter writer = new StringWriter()) {
            final Canvas canvas = Canvas.builder()
                    .replaceEchartsAsset(mockToReplaceDefaultEchartsResources)
                    .appendJSAssets(customJs)
                    .appendCssAssets(customCss)
                    .build()
//                    .renderTo(new PrintWriter(System.out))
                    .renderTo(writer);

            String html = writer.toString();
            Document doc = Jsoup.parse(html);
            // title
            Assertions.assertEquals(defaultTitle, doc.title());

            // update title
            canvas.asBuilder()
                    .title(title)
                    .build()
                    .renderTo(writer);
            html = writer.toString();
            doc = Jsoup.parse(html);


            // echarts
            Elements echartsScriptEl = doc.select(DOMHelper.EchartsAssetSelector);
            final String actualEChartsAsset = echartsScriptEl.attr(DOMHelper.SRC);
            Assertions.assertEquals(mockToReplaceDefaultEchartsResources, actualEChartsAsset);
            // customJs
            Elements customJsEl = doc.select(DOMHelper.CustomJSAssetSelector);
            final String customJsAsset = customJsEl.attr(DOMHelper.SRC);
            Assertions.assertEquals(customJsAsset, customJs);

            // customCss
            Elements customCssEl = doc.select(DOMHelper.CustomCssAssetSelector);
            final String customCssAsset = customCssEl.attr(DOMHelper.HREF);
            Assertions.assertEquals(customCssAsset, customCss);

        }
    }

    @Test
    void shouldRenderCorrect_WhenCallTheCanvas_GivenConfigAndOneChart() throws IOException {

        final String defaultContainerSize = "height:600px;width:900px;";
        final String chartTitle = "My Charts";
        final String legendFormatter = "'Legend {name}'";
        final String seriesName = "seriesName";
        final String chartType = "bar";

        final GenericChart c = GenericChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .legend(Legend.builder()
                                .formatter(FuncStr.of(legendFormatter)).build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(GenericSeriesOption.builder()
                                .name(seriesName)
                                .type(chartType)
                                .data(data1)
                                .build())
                )
                .build();

        try (StringWriter writer = new StringWriter()) {
            final Canvas cvs = Canvas.builder()
                    .addCharts(c)
                    .build();
            final Render render = RenderProvider.get();
            render.render(cvs, writer);

            String html = writer.toString();
            Document doc = Jsoup.parse(html);
            final Element containerEl = doc.select(DOMHelper.ContainerSelector).first();
            final String containerSize = containerEl.attr(DOMHelper.STYLE);
            Assertions.assertEquals(defaultContainerSize, containerSize);

            final Element chartScriptEl = doc.select(DOMHelper.ChartScriptSelector).first();
            final String chartRawId = chartScriptEl.attr(DOMHelper.ChartScriptChartIdAttr);
            final String wholeScript = chartScriptEl.dataNodes().get(0).getWholeData();

            final Pattern pattern = DOMHelper.patternProvider(chartRawId).get();
            final Matcher matcher = pattern.matcher(wholeScript);
            if (!matcher.find()) {
                Assertions.fail();
            }

            final String options = matcher.group(1);
            final String chartGeneratedTitle = JsonPath.read(options, "$.title.text");
            Assertions.assertEquals(chartTitle, chartGeneratedTitle);

            final String chartGeneratedType = JsonPath.read(options, "$.series[0].type");
            Assertions.assertEquals(chartType, chartGeneratedType);

            final String formatter = JsonPath.read(options, "$.legend.formatter");
            Assertions.assertEquals(legendFormatter, "'" + formatter + "'");

        }

    }

    @Test
    void shouldRenderCorrect_WhenCallTheCanvas_GivenConfigAndOneChartToUpdate() throws IOException {

        final String seriesName = "seriesName";
        final String chartType = "line";
        final String chartNewType = "bar";
        final String chartId = "myChartId";

        final GenericChart c = GenericChart.builder()
                .chartId(chartId)
                .options(ChartOption.builder()
                        .build()
                        .addSeries(GenericSeriesOption.builder()
                                .name(seriesName)
                                .type(chartType)
                                .data(data1)
                                .build())
                )
                .build();

        try (StringWriter writer = new StringWriter()) {
            final Canvas cvs = Canvas.builder()
                    .addCharts(c)
                    .build()
                    .asBuilder()
                    .<GenericChart>updateChart(chartId, it ->
                            it.ifPresent(chart ->
                                    chart.getChartOptions().getSeries()
                                            .forEach(s -> s.setType(chartNewType))))
                    .build();

            final Render render = RenderProvider.get();
            render.render(cvs, writer);

            String html = writer.toString();
            Document doc = Jsoup.parse(html);

            final Element chartScriptEl = doc.select(DOMHelper.ChartScriptSelector).first();
            final String chartRawId = chartScriptEl.attr(DOMHelper.ChartScriptChartIdAttr);

            Assertions.assertEquals(chartId, chartRawId);

            final String wholeScript = chartScriptEl.dataNodes().get(0).getWholeData();

            final Pattern pattern = DOMHelper.patternProvider(chartRawId).get();
            final Matcher matcher = pattern.matcher(wholeScript);
            if (!matcher.find()) {
                Assertions.fail();
            }

            final String options = matcher.group(1);
            final String chartGeneratedType = JsonPath.read(options, "$.series[0].type");
            Assertions.assertEquals(chartNewType, chartGeneratedType);
        }

    }

    @Test
    void shouldRenderCorrect_WhenCallTheCanvas_GivenConfigAndTwoCharts() throws IOException {

        final String defaultContainerSize = "height:600px;width:900px;";
        final String chartTitle = "My Charts";
        final String legendFormatter = "'Legend {name}'";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";
        final LinkedList<String> chartTypes = new LinkedList<>();
        chartTypes.add("line");
        chartTypes.add("bar");
        chartTypes.add("line");
        chartTypes.add("bar");

        final GenericChart c = GenericChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .legend(Legend.builder()
                                .formatter(FuncStr.of(legendFormatter)).build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(GenericSeriesOption.builder()
                                .name(seriesName2)
                                .type(chartTypes.pop())
                                .data(data2)
                                .build())
                )
                .build();

        final GenericChart c1 = GenericChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .legend(Legend.builder()
                                .formatter(FuncStr.of(legendFormatter)).build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(GenericSeriesOption.builder()
                                .name(seriesName)
                                .type(chartTypes.pop())
                                .data(data1)
                                .build())
                )
                .build();

        try (StringWriter writer = new StringWriter()) {
            final Canvas cvs = Canvas.builder()
                    .addCharts(c, c1)
                    .build();
            cvs.renderTo(new File("./test2charts.html"));
            final Render render = RenderProvider.get();
            render.render(cvs, writer);

            String html = writer.toString();
            Document doc = Jsoup.parse(html);
            final Elements containerEls = doc.select(DOMHelper.ContainerSelector);
            for (Element containerEl : containerEls) {
                final String containerSize = containerEl.attr(DOMHelper.STYLE);
                Assertions.assertEquals(defaultContainerSize, containerSize);

                final Element chartScriptEl = doc.select(DOMHelper.ChartScriptSelector).first();
                final String chartRawId = chartScriptEl.attr(DOMHelper.ChartScriptChartIdAttr);
                final String wholeScript = chartScriptEl.dataNodes().get(0).getWholeData();

                final Pattern pattern = DOMHelper.patternProvider(chartRawId).get();
                final Matcher matcher = pattern.matcher(wholeScript);
                if (!matcher.find()) {
                    Assertions.fail();
                }

                final String options = matcher.group(1);
                final String chartGeneratedTitle = JsonPath.read(options, "$.title.text");
                Assertions.assertEquals(chartTitle, chartGeneratedTitle);

                final String chartGeneratedType = JsonPath.read(options, "$.series[0].type");
                Assertions.assertTrue(chartTypes.contains(chartGeneratedType));

                final String formatter = JsonPath.read(options, "$.legend.formatter");
                Assertions.assertEquals(legendFormatter, "'" + formatter + "'");
            }
        }
    }


    @Test
    void shouldRenderCorrect_WhenCallTheCanvas_GivenConfigAndChartsOverlap() throws IOException {

        final String defaultContainerSize = "height:600px;width:900px;";
        final String chartTitle = "My Charts";
        final String legendFormatter = "'Legend {name}'";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";
        final String baseChartType = "line";
        final String overlapChartType = "bar";

        final GenericChart c = GenericChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder()
                                .text(chartTitle).build())
                        .legend(Legend.builder()
                                .formatter(FuncStr.of(legendFormatter)).build())
                        .xAxis(XAxis.builder()
                                .data(x)
                                .build())
                        .yAxis(YAxis.builder().build())
                        .build()
                        .addSeries(GenericSeriesOption.builder()
                                .name(seriesName)
                                .type(baseChartType)
                                .data(data1)
                                .build())
                        .addSeries(GenericSeriesOption.builder()
                                .name(seriesName2)
                                .type(overlapChartType)
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
            render.render(cvs, new PrintWriter(System.out));
            render.render(cvs, new FileWriter(new File("test-over.html")));

            String html = writer.toString();
            Document doc = Jsoup.parse(html);
            final Element containerEl = doc.select(DOMHelper.ContainerSelector).first();
            final String containerSize = containerEl.attr(DOMHelper.STYLE);
            Assertions.assertEquals(defaultContainerSize, containerSize);

            final Element chartScriptEl = doc.select(DOMHelper.ChartScriptSelector).first();
            final String chartRawId = chartScriptEl.attr(DOMHelper.ChartScriptChartIdAttr);
            final String wholeScript = chartScriptEl.dataNodes().get(0).getWholeData();

            final Pattern pattern = DOMHelper.patternProvider(chartRawId).get();
            final Matcher matcher = pattern.matcher(wholeScript);
            if (!matcher.find()) {
                Assertions.fail();
            }

            final String options = matcher.group(1);
            final String chartGeneratedTitle = JsonPath.read(options, "$.title.text");
            Assertions.assertEquals(chartTitle, chartGeneratedTitle);

            final String chartGeneratedType = JsonPath.read(options, "$.series[0].type");
            Assertions.assertEquals(baseChartType, chartGeneratedType);

            final String formatter = JsonPath.read(options, "$.legend.formatter");
            Assertions.assertEquals(legendFormatter, "'" + formatter + "'");

        }
    }

    @Test
    void shouldRenderCorrect_WhenCallTheCanvas_GivenInstanceInjectionAndFunctions() {

        String legendFormatter = "function (name) {\n" +
                "    return 'Legend ' + name;\n" +
                "}";

        String customFunc = "const show =(name)=>{\n" +
                "    return %MY_ECHARTS%.setOption(name)" +
                "};";

        try {
            Canvas.builder()
                    .title("My Charts With Functions")
                    .addCharts(
                            GenericChart.builder()
                                    .options(ChartOption.builder()
                                            .legend(Legend.builder()
                                                    .formatter(FuncStr.of(legendFormatter)).build())
                                            .build()
                                    )
                                    .build()
                                    .addJSFunctions(FuncStr.of(customFunc))
                    ).build()
                    .renderTo(new PrintWriter(System.out));

        } catch (Exception ex) {
            Assertions.fail();
        }
    }

}