package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.Tooltip;
import com.github.koooooo7.echarts4j.option.series.funnel.FunnelChartSeries;
import com.github.koooooo7.echarts4j.option.series.funnel.ItemStyle;
import com.github.koooooo7.echarts4j.option.series.funnel.Label;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class FunnelChartTests {

    @Test
    void shouldRenderCorrectly_WhenRenderTheFunnelCharts_GivenRelatedConfigs() {

        Canvas.builder()
                .addCharts(FunnelChart.builder()
                        .options(ChartOption.builder()
                                .title(Title.builder().text("Funnel").build())
                                .tooltip(Tooltip.builder()
                                        .trigger("item")
                                        .formatter(FuncStr.ofStr("{a} <br/>{b} : {c}%"))
                                        .build())
                                .legend(Legend.builder()
                                        .data(Arrays.asList("Show", "Click", "Visit", "Inquiry", "Order"))
                                        .build())
                                .build()
                                .addSeries(FunnelChartSeries.builder()
                                        .name("Funnel")
                                        .left("10%")
                                        .top("60")
                                        .bottom("60")
                                        .width("80%")
                                        .min(0)
                                        .max(100)
                                        .minSize("0%")
                                        .maxSize("100%")
                                        .sort(FuncStr.ofStr("descending"))
                                        .label(Label.builder().show(true).position(FuncStr.ofStr("inside")).build())
                                        .itemStyle(ItemStyle.builder().borderColor(FuncStr.ofStr("#fff")).build())
                                        .data(Arrays.asList(
                                                FunnelChart.FunnelDataItem.builder().name("Basic").value(60).build(),
                                                FunnelChart.FunnelDataItem.builder().name("Primary").value(30).build(),
                                                FunnelChart.FunnelDataItem.builder().name("Cool").value(10).build(),
                                                FunnelChart.FunnelDataItem.builder().name("OMG").value(90).build(),
                                                FunnelChart.FunnelDataItem.builder().name("ACE").value(120).build()
                                        ))

                                        .build())
                        )
                        .build())
                .build()
                .render();


    }


}