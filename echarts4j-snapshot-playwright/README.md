# echarts4j-snapshot-playwright

*🍩 An echarts4j extension for image render.*

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
   implementation 'com.github.Koooooo-7.echarts4j:echarts4j-snapshot-playwright:main-SNAPSHOT'
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
    <artifactId>echarts4j-snapshot-playwright</artifactId>
    <version>main-SNAPSHOT</version>
</dependency>
```

</details>

---

### 📝 RunBook

It uses the [Playwright for Java](https://playwright.dev/java/) to snapshot the rendered chart.
Support image format: `JPG/JPEG`,`PNG`.

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
                        .addSeries(LineChartSeriesOption.builder()
                                .name(seriesName)
                                .data(data1)
                                .build())
                        .addSeries(BarChartSeriesOption.builder()
                                .name(seriesName2)
                                .data(data2)
                                .build())
                )
                .build();

        Canvas.builder()
                .addCharts(c)
                .build()
                .renderTo(new File("myLineImage.jpg");
```

### 📃 License

MIT [©KoyZhuang](https://github.com/Koooooo-7/echarts4j/blob/main/LICENSE)