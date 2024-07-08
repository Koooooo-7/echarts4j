package com.github.koooooo7.echarts4j.option.series.sunburst;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.option.series.GenericSeriesOption;
import com.github.koooooo7.echarts4j.option.series.SeriesOption;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class SunburstChartSeries extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Sunburst.getType();
    private List<?> data;
    private Label label;
    private ItemStyle itemStyle;
    private Emphasis emphasis;
    private MarkArea markArea;
    private MarkLine markLine;
    private MarkPoint markPoint;
    private Tooltip tooltip;


}
