package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.helper.DataHelper;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.series.PieChartSeries;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

public class PieChartTests {

    @Test
    void shouldGeneratePieChart_WhenCallThePieChartConfig_GivenNecessaryConfigs() {
        final List<LinkedHashMap<String, Object>> data = DataHelper.create()
                .addValueField(Integer.class)
                .addNameField().build()
                .addData(1048, "Search Engine")
                .addData(735, "Direct")
                .addData(580, "Email")
                .addData(484, "Union Ads")
                .addData(300, "Video Ads")
                .get();
        Canvas.builder()
                .layout(Canvas.CanvasLayout.NONE)
                .addCharts(PieChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Pie Chart").build())
                                .build()
                                .addSeries(PieChartSeries.builder()
                                        .data(data).build()))
                        .build())
                .build()
                .render();

    }
}
