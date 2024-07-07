package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.DataZoom;
import com.github.koooooo7.echarts4j.option.chart.Grid;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.chart.Tooltip;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.embedded.series.ItemStyle;
import com.github.koooooo7.echarts4j.option.series.CandlestickChartSeries;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class CandlestickChartTests {


    @Test
    void shouldRenderCorrectly_WhenRenderTheCandlestickChart_GivenRelatedConfigs() {

        final List<Object> data = new ArrayList<>();

        // uniform data
        for (int i = 0; i < 30; i++) {
            int[] item = new int[4];
            for (int j = 0; j < 4; j++) {
                item[j] = ThreadLocalRandom.current().nextInt(2000, 3050);
            }
            data.add(item);
        }

        List<Integer> specific = new ArrayList<>(9);
        for (int j = 0; j < 4; j++) {
            final int i = ThreadLocalRandom.current().nextInt(1000, 3050);
            specific.add(i);
        }
        // specific item
        CandlestickChart.CandlestickChartDataItem value = CandlestickChart.CandlestickChartDataItem.builder()
                .itemStyle(ItemStyle.builder()
                        .color(FuncStr.of("'rgba(47, 57, 166, 1)'"))
                        .build())
                .value(specific).build();

        data.add(24, value);

        final ChartOption options = ChartOption
                .builder()
                .title(Title.builder().text("CandlestickChart Sample").build())
                .tooltip(Tooltip.builder()
                        .trigger("axis")
                        .axisPointer(Tooltip.AxisPointer.builder()
                                .type("cross").build())
                        .build()
                )
                .legend(Legend.builder()
                        .data(Arrays.asList("MA250", "MA360"))
                        .build())
                .grid(Grid.builder()
                        .left("10%")
                        .right("10%")
                        .bottom("15%")
                        .build())
                .xAxis(XAxis.builder()
                        .type("category")
                        .data(Arrays.asList("2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30", "2013/1/31", "2013/2/1", "2013/2/4", "2013/2/5", "2013/2/6", "2013/2/7", "2013/2/8", "2013/2/18", "2013/2/19", "2013/2/20", "2013/2/21", "2013/2/22", "2013/2/25", "2013/2/26", "2013/2/27", "2013/2/28", "2013/3/1", "2013/3/4", "2013/3/5", "2013/3/6", "2013/3/7", "2013/3/8", "2013/3/11", "2013/3/12", "2013/3/13", "2013/3/14", "2013/3/15", "2013/3/18", "2013/3/19", "2013/3/20", "2013/3/21", "2013/3/22", "2013/3/25", "2013/3/26", "2013/3/27", "2013/3/28", "2013/3/29", "2013/4/1", "2013/4/2", "2013/4/3", "2013/4/8", "2013/4/9", "2013/4/10", "2013/4/11", "2013/4/12", "2013/4/15", "2013/4/16", "2013/4/17", "2013/4/18", "2013/4/19", "2013/4/22", "2013/4/23", "2013/4/24", "2013/4/25", "2013/4/26", "2013/5/2", "2013/5/3", "2013/5/6", "2013/5/7", "2013/5/8", "2013/5/9", "2013/5/10", "2013/5/13", "2013/5/14", "2013/5/15", "2013/5/16", "2013/5/17", "2013/5/20", "2013/5/21", "2013/5/22", "2013/5/23", "2013/5/24", "2013/5/27", "2013/5/28", "2013/5/29", "2013/5/30", "2013/5/31", "2013/6/3", "2013/6/4", "2013/6/5", "2013/6/6", "2013/6/7", "2013/6/13"))
                        .scale(true)
                        .boundaryGap(FuncStr.of(true))
                        .axisLine(XAxis.AxisLine.builder()
                                .onZero(false)
                                .build())
                        .splitLine(XAxis.SplitLine.builder()
                                .show(false)
                                .build())
                        .splitNumber(20)
                        .min(FuncStr.of("'dataMin'"))
                        .max(FuncStr.of("'dataMax'"))
                        .build())
                .yAxis(YAxis.builder()
                        .scale(true)
                        .splitArea(YAxis.SplitArea.builder()
                                .show(true)
                                .build())
                        .build())
                .dataZoom(Arrays.asList(DataZoom.InsideDataZoom.builder()
                                .start(50)
                                .end(100)
                                .build(),
                        DataZoom.SliderDataZoom.builder()
                                .show(true)
                                .start(50)
                                .end(100)
                                .top("90%")
                                .build()

                ))
                .build()
                .addSeries(CandlestickChartSeries.builder()
                        .name("X")
                        .data(data)
                        .build()
                );
        Canvas.builder()
                .addCharts(CandlestickChart
                        .builder()
                        .options(options).build())
                .build()
                .renderTo(new File("candlestick.html"));
    }

}