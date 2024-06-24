package com.github.koooooo7.echarts.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts.chart.ChartType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Line.getType();
    private String name;
    private Boolean show;
    private Object data;

}
