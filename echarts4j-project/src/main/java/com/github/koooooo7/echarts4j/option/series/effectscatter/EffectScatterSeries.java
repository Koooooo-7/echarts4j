package com.github.koooooo7.echarts4j.option.series.effectscatter;

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
public class EffectScatterSeries extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.EffectScatter.getType();
    private Label label;
    private ItemStyle itemStyle;
    private Emphasis emphasis;
    private MarkArea markArea;
    private MarkLine markLine;
    private MarkPoint markPoint;
    private Tooltip tooltip;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RippleEffect {
        private String color;
        private Integer number;
        private Integer period;
        private Float scale;
        private String brushType;

    }
}
