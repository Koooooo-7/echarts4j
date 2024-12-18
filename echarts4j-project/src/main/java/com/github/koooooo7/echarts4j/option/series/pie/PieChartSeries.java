package com.github.koooooo7.echarts4j.option.series.pie;

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
public class PieChartSeries extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Pie.getType();
    private Boolean clockwise;
    private Integer startAngle;
    private Integer endAngle;
    private Integer minAngle;
    private Integer padAngle;
    private Integer minShowLabelAngle;
    private String roseType;
    private Boolean avoidLabelOverlap;
    private Boolean stillShowZeroSum;
    private Integer percentPrecision;
    private Boolean showEmptyCircle;
    private List<?> data;
    private String radius;
    private Label label;
    private ItemStyle itemStyle;
    private AreaStyle areaStyle;
    private Emphasis emphasis;
    private MarkArea markArea;
    private MarkLine markLine;
    private MarkPoint markPoint;
    private Tooltip tooltip;

}
