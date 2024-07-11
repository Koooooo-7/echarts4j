# echarts4j-chart-server

*🍩 An echarts4j extension for chart live update.*

---

### 🔰 Installation

<details>
  <summary>Install via Gradle</summary>

```gradle
repositories {
    mavenCentral()
    // add the repository
    maven { url 'https://jitpack.io' }
}

dependencies {
   implementation 'com.github.Koooooo-7.echarts4j:echarts4j-chart-server:<Version rules same to echarts4j>'
}
```

</details>

<details>
  <summary>Install via Maven</summary>

```pom
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
	
<dependency>
    <groupId>com.github.Koooooo-7.echarts4j</groupId>
    <artifactId>echarts4j-chart-server</artifactId>
    <version>main-SNAPSHOT</version>
</dependency>
```
</details>

---

### 📝 RunBook

By default, it provides the chart server function on port `9394`.
And it uses the WebSocket to support the chart live update.

It all runs in async, so you need stop the main process exit.
A handy way is placing `SimpleChartServerRender.on();` on the last.

## Basic Server

> It will host on port `9394`.
> Use the `ECHARTS4J_CHART_SERVER_PORT` env param to change the port.

```
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

        Canvas.builder()
                .addCharts(c)
                .build()
                .render();
        // hanging the main process
        SimpleChartServerRender.on();
```

## Live Update

> It will do websocket on `localhost:9493`.  
> Use the `ECHARTS4J_CS_LIVE_UPDATE_HOST` env param to change the host.  
> Use the `ECHARTS4J_CS_LIVE_UPDATE_PORT` env param to change the port.

```
...
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
        
        // hanging the main process
        SimpleChartServerRender.on();

```

### 📃 License

Apache-2.0 license [©KoyZhuang](https://github.com/Koooooo-7/echarts4j/blob/main/LICENSE)