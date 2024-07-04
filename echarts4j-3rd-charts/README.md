# echarts4j-chart-server

*🍩 An extension for echarts4j importing 3rd party charts.*

---

### 🔰 Installation

// TODO

---

### 📊 Support 3rd-Charts

- Liquid-Fill
- WordCloud

### 📝 RunBook

#### Initial canvas with 3rd chart support, recommend.

```
 Enhanced3rdChartsCanvas.builder()
                .addCharts(LiquidFillChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Liquid Basic").build())
                                .build()
                                .addSeries(LiquidFillChartSeries.builder()
                                        .data(Arrays.asList("0.6", "0.5", "0.1")).build())
                        ).build())
                .addCharts(PieChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Pie Chart").build())
                                .build()
                                .addSeries(PieChartSeriesOption.builder()
                                        .data(data).build()))
                        .build()).build()
                .render();

```

---

#### Additional 3rd chart support for the exists canvas.

```
       // preset a chart in standard canvas
        final Canvas canvasExistPieChart = Canvas.builder()
                .layout(Canvas.CanvasLayout.NONE)
                .addCharts(PieChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Pie Chart").build())
                                .build()
                                .addSeries(PieChartSeriesOption.builder()
                                        .data(data).build()))
                        .build())
                .build();

        // box to 3rd support
        Enhanced3rdChartsCanvas.box3rdChartsSupport(canvasExistPieChart.asBuilder())
                .addCharts(LiquidFillChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Liquid Basic").build())
                                .build()
                                .addSeries(LiquidFillChartSeries.builder()
                                        .data(Arrays.asList("0.6", "0.5", "0.1")).build())
                        ).build())
                .build()
                .render();

```

> By default, it supports 3rd charts and other charts mixed in the same Canvas instance in one time.
> If you call the `builder` for the canvas again, you may need to re-`box3rdChartsSupport` also.

### 📃 License

MIT [©KoyZhuang](https://github.com/Koooooo-7/echarts4j/blob/main/LICENSE)