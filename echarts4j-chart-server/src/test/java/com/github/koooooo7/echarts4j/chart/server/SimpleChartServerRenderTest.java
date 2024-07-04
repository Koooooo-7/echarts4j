package com.github.koooooo7.echarts4j.chart.server;


import com.github.koooooo7.echarts4j.chart.BarChart;
import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.LineChart;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.series.BarChartSeries;
import com.github.koooooo7.echarts4j.option.series.LineChartSeries;
import com.github.koooooo7.echarts4j.option.series.SeriesOption;
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

class SimpleChartServerRenderTest {

    private static final CountDownLatch live = new CountDownLatch(1);

    private static final List<Object> data1 = new ArrayList<>();
    private static final List<Object> data2 = new ArrayList<>();
    private static final List<String> x = new ArrayList<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    void setup() {
        setupData1();
        setupData2();
        x.clear();
        for (int i = 0; i < 20; i++) {
            x.add(ThreadLocalRandom.current().nextInt(100) + "Street");
        }
    }

    void setupData1() {
        data1.clear();
        for (int i = 0; i < 20; i++) {
            data1.add(ThreadLocalRandom.current().nextInt(100));
        }

    }

    void setupData2() {
        data2.clear();
        for (int i = 0; i < 20; i++) {
            data2.add(ThreadLocalRandom.current().nextInt(100));
        }
    }

    //    @Test
    void multiRunnerRender() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    for (; ; ) {
                        setup();
                        shouldServerChart_WhenUseTheChartServer_GivenChartAndCallTheRender();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        SimpleChartServerRender.on();
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
        try {
            Canvas.builder()
                    .addCharts(c)
                    .build()
                    .render();
        } catch (Exception ex) {
            Assertions.fail();
        }

//        live.await();
    }

    @Test
    void shouldServerLiveUpdateChart_WhenUseTheChartServer_GivenChartWithCustomUpdateScheduling() throws InterruptedException {
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
        final Canvas canvas = Canvas.builder()
                .addCharts(c)
                .build();

        try {
            final LiveUpdatableCanvas liveUpdatableCanvas = ChartLiveUpdater.liveUpdateBoxed(canvas);
            // early start server
            liveUpdatableCanvas.emit();

            scheduler.scheduleAtFixedRate(
                    () -> liveUpdatableCanvas.liveUpdateChart(chartId, cp -> cp.ifPresent(myChart -> {
                                // refresh data
                                setup();
                                final ChartOption chartOptions = myChart.getChartOptions();
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
                            }))
                            .emit(),

                    5, 2, TimeUnit.SECONDS);

        } catch (Exception e) {
            Assertions.fail();
        }

        // not stop the main process
//        live.await();
    }

    @Test
    void shouldServerLiveUpdateChart_WhenUseTheChartServer_Given2ChartWithDifferentUpdateScheduling() {
        final String chartTitle = "My Live Update Chart";
        final String seriesName = "seriesName";
        final String seriesName2 = "seriesName2";
        final String chartId = "cId";
        final String chartId2 = "cId2";
        final LineChart c = LineChart.builder()
                .chartId(chartId)
                .height("300px")
                .width("420px")
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
                        .addSeries(LineChartSeries.builder()
                                .name(seriesName)
                                .data(data1)
                                .build())
                )
                .build();
        final BarChart b = BarChart.builder()
                .chartId(chartId2)
                .height("300px")
                .width("420px")
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
                        .addSeries(BarChartSeries.builder()
                                .name(seriesName2)
                                .data(data2)
                                .build())
                )
                .build();
        final Canvas canvas = Canvas.builder()
                .addCharts(c)
                .addCharts(b)
                .build();

        try {
            final LiveUpdatableCanvas liveUpdatableCanvas = ChartLiveUpdater.liveUpdateBoxed(canvas);
            // builtin scheduling
            scheduler.scheduleAtFixedRate(
                    () -> liveUpdatableCanvas.liveUpdateChart(chartId, cp -> cp.ifPresent(myChart -> {
                                // refresh data
                                setupData1();
                                final ChartOption chartOptions = myChart.getChartOptions();
                                final Title title = chartOptions.getTitle();
                                title.setSubtext("LastUpdateTime: " + LocalDateTime.now().format(DATE_TIME_FORMATTER));
                                final List<SeriesOption> series = chartOptions.getSeries();
                                for (SeriesOption so : series) {
                                    so.setData(data1);
                                }
                            }))
                            .emit(),
                    1, 5, TimeUnit.SECONDS);

            // builtin scheduling
            liveUpdatableCanvas.liveUpdateChartScheduling(chartId2, cp -> cp.ifPresent(myChart -> {
                        // refresh data
                        setupData2();
                        final ChartOption chartOptions = myChart.getChartOptions();
                        final Title title = chartOptions.getTitle();
                        title.setSubtext("LastUpdateTime: " + LocalDateTime.now().format(DATE_TIME_FORMATTER));
                        final List<SeriesOption> series = chartOptions.getSeries();
                        for (SeriesOption so : series) {
                            so.setData(data2);
                        }
                    }),

                    10, 10, TimeUnit.SECONDS).emit();
        } catch (Exception e) {
            Assertions.fail();
        }

//        SimpleChartServerRender.on();
    }


}