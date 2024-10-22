package com.github.koooooo7.echarts4j.option;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.koooooo7.echarts4j.option.chart.DataZoom;
import com.github.koooooo7.echarts4j.option.chart.Grid;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Radar;
import com.github.koooooo7.echarts4j.option.chart.Toolbox;
import com.github.koooooo7.echarts4j.option.chart.Tooltip;
import com.github.koooooo7.echarts4j.option.chart.VisualMap;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.option.series.SeriesOption;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.JsonUtil;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChartOption {
    private Title title;
    private Legend legend;
    private Grid grid;
    @JsonProperty("xAxis")
    private XAxis xAxis;
    @JsonProperty("yAxis")
    private YAxis yAxis;
    private Radar radar;
    private List<? extends DataZoom> dataZoom;
    private List<? extends VisualMap> visualMap;
    private Tooltip tooltip;
    private Toolbox toolbox;
    private Object color;
    private Boolean animation;
    private Integer animationThreshold;
    private FuncStr animationDuration;
    private FuncStr animationDelay;
    private Boolean useUTC;
    private final List<SeriesOption> series = new ArrayList<>();

    public final ChartOption addSeries(SeriesOption seriesOption) {
        series.add(seriesOption);
        return this;
    }

}
