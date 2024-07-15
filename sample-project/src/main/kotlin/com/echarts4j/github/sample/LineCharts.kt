package com.echarts4j.github.sample

import com.github.koooooo7.echarts4j.chart.Canvas
import com.github.koooooo7.echarts4j.chart.LineChart
import com.github.koooooo7.echarts4j.option.ChartOption
import com.github.koooooo7.echarts4j.option.chart.Legend
import com.github.koooooo7.echarts4j.option.chart.Legend.LegendBuilder
import com.github.koooooo7.echarts4j.option.chart.Title
import com.github.koooooo7.echarts4j.option.chart.Toolbox
import com.github.koooooo7.echarts4j.option.chart.Toolbox.SaveAsImage
import com.github.koooooo7.echarts4j.option.series.line.Label
import com.github.koooooo7.echarts4j.option.series.line.LineChartSeries
import com.github.koooooo7.echarts4j.type.FuncStr
import java.io.File


fun main() {
    basicLineCharts()
}

private fun basicLineCharts() {
    Canvas.builder()
        .addCharts(
            LineChart.builder()
                .options(
                    ChartOption
                        .builder()
                        .title(Title.builder().text("Basic Line").build())
                        .toolbox(
                            Toolbox.builder()
                                .feature(
                                    Toolbox.Feature
                                        .builder().saveAsImage(SaveAsImage.builder().build())
                                        .build()
                                ).build()
                        ).build().addSeries(
                            LineChartSeries.builder()
                                .data(listOf(1, 2, 3, 4, 5, 6))
                                .build()
                        )
                ).build()
        )
        .build()
        .renderTo(File("./sample-project/generated/basic-line.html"))
}

