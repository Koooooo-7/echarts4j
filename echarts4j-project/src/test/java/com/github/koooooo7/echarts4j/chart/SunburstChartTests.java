package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.embedded.series.Label;
import com.github.koooooo7.echarts4j.option.series.SunburstChartSeries;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SunburstChartTests {


    @Test
    void shouldGenerateSunburstChart_WhenCallTheSunburstChartConfig_GivenNecessaryConfigs() {
        final List<SunburstChart.SunburstDataItem> data = new ArrayList<>();
        data.add(SunburstChart.SunburstDataItem.builder()
                .name("Uncle Joe")
                .value(25)
                .children(Arrays.asList(SunburstChart.SunburstDataItem
                                .builder()
                                .name("Cousin Mary")
                                .value(2).build(),
                        SunburstChart.SunburstDataItem
                                .builder()
                                .name("Cousin Pipe")
                                .value(4).build()
                ))
                .build());
        data.add(SunburstChart.SunburstDataItem.builder()
                .name("Father")
                .value(14)
                .children(Arrays.asList(SunburstChart.SunburstDataItem
                                .builder()
                                .name("Self")
                                .value(5).build(),
                        SunburstChart.SunburstDataItem
                                .builder()
                                .name("Sister Cole")
                                .value(1).build()
                ))
                .build());
        data.add(SunburstChart.SunburstDataItem.builder()
                .name("Ben")
                .children(Arrays.asList(SunburstChart.SunburstDataItem
                                .builder()
                                .name("Cousin Betty")
                                .value(1).build(),
                        SunburstChart.SunburstDataItem
                                .builder()
                                .name("Cousin Jan")
                                .value(2).build()
                ))
                .build());

        Canvas.builder()
                .addCharts(SunburstChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Sunburst Chart").build())
                                .build()
                                .addSeries(SunburstChartSeries.builder()
                                        .label(Label.builder().rotate(FuncStr.of("'radial'")).build())
                                        .data(data).build()))
                        .build())
                .build()
                .render();

    }

}