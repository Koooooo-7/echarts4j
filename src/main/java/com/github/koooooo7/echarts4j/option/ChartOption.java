package com.github.koooooo7.echarts.option;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.koooooo7.echarts.option.chart.Legend;
import com.github.koooooo7.echarts.option.chart.XAxis;
import com.github.koooooo7.echarts.option.chart.YAxis;
import com.github.koooooo7.echarts.option.series.SeriesOption;
import com.github.koooooo7.echarts.option.chart.Title;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.LinkedList;
import java.util.List;

@SuperBuilder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChartOption {
    private Title title;
    @JsonProperty("xAxis")
    private XAxis xAxis;
    @JsonProperty("yAxis")
    private YAxis yAxis;
    private Legend legend;
    private final List<SeriesOption> series = new LinkedList<>();

    public final ChartOption addSeries(SeriesOption seriesOption) {
        series.add(seriesOption);
        return this;
    }

}
