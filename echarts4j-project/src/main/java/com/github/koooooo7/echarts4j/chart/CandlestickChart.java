package com.github.koooooo7.echarts4j.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.embedded.series.Emphasis;
import com.github.koooooo7.echarts4j.option.embedded.series.ItemStyle;
import com.github.koooooo7.echarts4j.option.embedded.series.Tooltip;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CandlestickChart extends BaseChart<com.github.koooooo7.echarts4j.chart.BarChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Candlestick;

    @Override
    public void postProcessor() {
        super.postProcessor();
        if (Objects.isNull(getChartOptions().getXAxis())) {
            getChartOptions().setXAxis(XAxis.builder().build());
        }
        if (Objects.isNull(getChartOptions().getYAxis())) {
            getChartOptions().setYAxis(YAxis.builder().build());
        }
    }

    @Data
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CandlestickChartDataItem {
        private String name;
        private List<?> value;
        private String groupId;
        private String childGroupId;
        private ItemStyle itemStyle;
        private Emphasis emphasis;
        private Tooltip tooltip;

    }
}
