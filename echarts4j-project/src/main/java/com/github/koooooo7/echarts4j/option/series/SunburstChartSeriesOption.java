package com.github.koooooo7.echarts4j.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.option.embedded.Emphasis;
import com.github.koooooo7.echarts4j.option.embedded.ItemStyle;
import com.github.koooooo7.echarts4j.option.embedded.Label;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class SunburstChartSeriesOption extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Sunburst.getType();
    private List<?> data;


}
