package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.helper.DataHelper;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Radar;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.series.radar.RadarChartSeries;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RadarChartTests {
    @Test
    void shouldGenerateScatterChart_WhenCallThePieChartConfig_GivenNecessaryConfigs() {
        List<Integer> item = new ArrayList<>();
        List<Integer> item2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            item.add(ThreadLocalRandom.current().nextInt(5000, 16000));
            item2.add(ThreadLocalRandom.current().nextInt(5000, 16000));
        }

        final List<LinkedHashMap<String, Object>> finalData = DataHelper.create()
                .addNameField()
                .addValueField(List.class)
                .build()
                .addData("Promotion", item)
                .addData("Baseline", item2)
                .get();
        final List<String> legendDate = new ArrayList<>();
        legendDate.add("Allocated Budget");
        legendDate.add("Actual Spending");

        final List<Radar.IndicatorDataItem> indicator = new ArrayList<>();
        indicator.add(Radar.IndicatorDataItem.builder().name("Sales").max(16500).build());
        indicator.add(Radar.IndicatorDataItem.builder().name("Administration").max(26000).build());
        indicator.add(Radar.IndicatorDataItem.builder().name("Information Technology").max(35000).build());
        indicator.add(Radar.IndicatorDataItem.builder().name("Customer Support").max(16500).build());
        indicator.add(Radar.IndicatorDataItem.builder().name("Development").max(65000).build());
        indicator.add(Radar.IndicatorDataItem.builder().name("Marketing").max(16500).build());

        Canvas.builder()
                .addCharts(RadarChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Basic Radar Chart").build())
                                .legend(Legend.builder().data(legendDate).build())
                                .radar(Radar.builder()
                                        .indicator(indicator)
                                        .build())
                                .build()
                                .addSeries(RadarChartSeries.builder()
                                        .name("P&L")
                                        .data(finalData)
                                        .build()))
                        .build())
                .build()
                .render();

    }
}
