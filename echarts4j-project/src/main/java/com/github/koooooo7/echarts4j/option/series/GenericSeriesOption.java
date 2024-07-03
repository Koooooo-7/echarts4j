package com.github.koooooo7.echarts4j.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.option.embedded.AreaStyle;
import com.github.koooooo7.echarts4j.option.embedded.Emphasis;
import com.github.koooooo7.echarts4j.option.embedded.ItemStyle;
import com.github.koooooo7.echarts4j.option.embedded.Label;
import com.github.koooooo7.echarts4j.option.embedded.LineStyle;
import com.github.koooooo7.echarts4j.option.embedded.TextStyle;
import com.github.koooooo7.echarts4j.option.embedded.Tooltip;
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
    private Label label;
    private ItemStyle itemStyle;
    private LineStyle lineStyle;
    private AreaStyle areaStyle;
    private Emphasis emphasis;
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
    private Tooltip tooltip;
    private TextStyle textStyle;


}
