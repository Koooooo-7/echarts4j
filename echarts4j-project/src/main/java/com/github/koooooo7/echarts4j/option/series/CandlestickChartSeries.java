package com.github.koooooo7.echarts4j.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class CandlestickChartSeries extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Candlestick.getType();
    private String barWidth;
    private String barMaxWidth;
    private String barMinWidth;
    private List<?> data;


}
