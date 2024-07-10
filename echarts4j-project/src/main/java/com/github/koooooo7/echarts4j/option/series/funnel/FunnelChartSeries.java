package com.github.koooooo7.echarts4j.option.series.funnel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.option.series.GenericSeriesOption;
import com.github.koooooo7.echarts4j.option.series.SeriesOption;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class FunnelChartSeries extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Funnel.getType();
    private List<?> data;
    private Label label;
    private Integer min;
    private Integer max;
    private String minSize;
    private String maxSize;
    private FuncStr sort;
    private ItemStyle itemStyle;
    private MarkPoint markPoint;
    private MarkLine markLine;
    private MarkArea markArea;
    private Tooltip tooltip;


}
