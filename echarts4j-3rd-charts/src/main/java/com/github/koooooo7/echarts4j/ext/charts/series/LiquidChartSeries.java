package com.github.koooooo7.echarts4j.ext.charts.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.ext.charts.chart.Chart3rdType;
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
public class LiquidChartSeries extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = Chart3rdType.Liquid.getType();
}
