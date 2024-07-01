package com.github.koooooo7.echarts4j.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.option.series.embed.SeriesTextStyle;
import com.github.koooooo7.echarts4j.option.series.embed.SeriesTooltip;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Generic.getType();
    private String id;
    private String name;
    private String colorBy;
    private String coordinateSystem;
    private Integer xAxisIndex;
    private Integer yAxisIndex;
    private Integer polarIndex;
    private Integer geoIndex;
    private Integer calendarIndex;
    private String symbol;
    private FuncStr symbolSize;
    private FuncStr symbolRotate;
    private Boolean showSymbol;
    private Boolean showAllSymbol;
    private String stack;
    private String stackStrategy;
    private String cursor;
    private Boolean connectNulls;
    private Boolean clip;
    private Boolean show;
    private Object data;
    private Boolean large;
    private Integer largeThreshold;
    private List<?> dimensions;
    private String left;
    private String top;
    private String right;
    private String bottom;
    private String width;
    private String height;
    private List<?> center;
    private Boolean animation;
    private Integer animationThreshold;
    private FuncStr animationDuration;
    private Integer animationEasing;
    private FuncStr animationDelay;
    private FuncStr animationDurationUpdate;
    private String animationEasingUpdate;
    private FuncStr animationDelayUpdate;
    private SeriesTooltip tooltip;
    private SeriesTextStyle textStyle;


}
