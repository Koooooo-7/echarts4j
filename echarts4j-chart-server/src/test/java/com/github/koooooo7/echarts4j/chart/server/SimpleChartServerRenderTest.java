package com.github.koooooo7.echarts4j.chart.server;


import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.LineChart;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.series.BarChartSeriesOption;
import com.github.koooooo7.echarts4j.option.series.ListChartSeriesOption;
import com.github.koooooo7.echarts4j.option.series.SeriesOption;
import com.github.koooooo7.echarts4j.render.Render;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

class SimpleChartServerRenderTest {

    private static final CountDownLatch live = new CountDownLatch(1);

    private static final List<Object> data1 = new ArrayList<>();
    private static final List<Object> data2 = new ArrayList<>();
    private static final List<String> x = new ArrayList<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    void setup() {
        data1.clear();
        data2.clear();
        x.clear();
        for (int i = 0; i < 20; i++) {
            data1.add(ThreadLocalRandom.current().nextInt(100));
            data2.add(ThreadLocalRandom.current().nextInt(100));
            x.add(ThreadLocalRandom.current().nextInt(100) + "Street");
        }

    }

    @Test
    void shouldServerChart_WhenUseTheChartServer_GivenChartAndCallTheRender() throws InterruptedException {
        final String chartTitle = "My Live Update Chart";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";
        final String chartId = "cId";
        final LineChart c = LineChart.builder()
                .chartId(chartId)
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
        final Canvas canvas = Canvas.builder()
                .addCharts(c)
                .build();

        try {
            final Canvas boxed = DynamicChartLiveUpdater.liveUpdateBoxed(canvas, updater(chartId));
            final Render render = boxed.extractRender();
            render.render(boxed);
        } catch (Exception e) {
            Assertions.fail();
        }

        // not stop the main process
//        live.await();
    }

    private Consumer<Canvas> updater(String chartId) {
        return proxy -> scheduler.scheduleAtFixedRate(() -> {
            // refresh data
            setup();
            proxy.asBuilder().updateChart(chartId, c1 -> c1.ifPresent(it -> {
                final ChartOption chartOptions = it.getChartOptions();
                final Title title = chartOptions.getTitle();
                title.setSubtext("LastUpdateTime: " + LocalDateTime.now().format(DATE_TIME_FORMATTER));
                boolean first = true;
                final List<SeriesOption> series = chartOptions.getSeries();
                for (SeriesOption so : series) {
                    if (first) {
                        so.setData(data1);
                    } else {
                        so.setData(data2);
                    }
                    first = false;

                }
            }));
        }, 5, 5, TimeUnit.SECONDS);
    }

}