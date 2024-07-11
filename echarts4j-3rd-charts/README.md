# echarts4j-chart-server

*🍩 An extension for echarts4j importing 3rd party charts.*

---

### 🔰 Installation

It hosts on [jitpack](https://jitpack.io/#Koooooo-7/echarts4j/main-SNAPSHOT).

<details>
  <summary>Install via Gradle</summary>

```gradle
repositories {
    mavenCentral()
    // add the repository
    maven { url 'https://jitpack.io' }
}

dependencies {
   implementation 'com.github.Koooooo-7.echarts4j:echarts4j-3rd-charts:main-SNAPSHOT'
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
    <artifactId>echarts4j-3rd-charts</artifactId>
    <version>main-SNAPSHOT</version>
</dependency>
```

</details>

---

### 📊 Support 3rd-Charts

- Liquid-Fill
- WordCloud

### 📝 RunBook

#### Initial canvas with 3rd chart support, recommend.

```
         Canvas.builder()
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
        canvasExistPieChart.asBuilder())
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

### 📃 License

Apache-2.0 license [©KoyZhuang](https://github.com/Koooooo-7/echarts4j/blob/main/LICENSE)