package com.github.koooooo7.echarts4j.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Generic.getType();
    private String name;
    private Boolean show;
    private Object data;
}
