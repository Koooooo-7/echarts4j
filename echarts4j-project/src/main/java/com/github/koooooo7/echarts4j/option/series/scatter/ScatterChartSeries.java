package com.github.koooooo7.echarts4j.option.series.scatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.option.series.GenericSeriesOption;
import com.github.koooooo7.echarts4j.option.series.SeriesOption;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class ScatterChartSeries extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Scatter.getType();
    private Label label;
    private ItemStyle itemStyle;
    private Emphasis emphasis;
    private MarkArea markArea;
    private MarkLine markLine;
    private MarkPoint markPoint;
    private Tooltip tooltip;
}
