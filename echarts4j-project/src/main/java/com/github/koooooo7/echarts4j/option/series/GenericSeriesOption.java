package com.github.koooooo7.echarts4j.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.option.embedded.series.AreaStyle;
import com.github.koooooo7.echarts4j.option.embedded.series.Emphasis;
import com.github.koooooo7.echarts4j.option.embedded.series.ItemStyle;
import com.github.koooooo7.echarts4j.option.embedded.series.Label;
import com.github.koooooo7.echarts4j.option.embedded.series.LineStyle;
import com.github.koooooo7.echarts4j.option.embedded.series.MarkArea;
import com.github.koooooo7.echarts4j.option.embedded.series.MarkLine;
import com.github.koooooo7.echarts4j.option.embedded.series.MarkPoint;
import com.github.koooooo7.echarts4j.option.embedded.series.TextStyle;
import com.github.koooooo7.echarts4j.option.embedded.series.Tooltip;
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
    private Boolean legendHoverLink;
    private Integer xAxisIndex;
    private Integer yAxisIndex;
    private Integer polarIndex;
    private Integer geoIndex;
    private Integer calendarIndex;
    private String layout;
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
    private MarkArea markArea;
    private MarkLine markLine;
    private MarkPoint markPoint;
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
