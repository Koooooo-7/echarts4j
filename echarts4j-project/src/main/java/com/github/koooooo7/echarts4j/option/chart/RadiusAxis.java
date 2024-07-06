package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.embedded.chart.AxisLine;
import com.github.koooooo7.echarts4j.option.embedded.chart.SplitArea;
import com.github.koooooo7.echarts4j.option.embedded.chart.SplitLine;
import com.github.koooooo7.echarts4j.option.embedded.series.AxisPointer;
import com.github.koooooo7.echarts4j.option.embedded.series.NameTextStyle;
import com.github.koooooo7.echarts4j.option.embedded.series.NameTruncate;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RadiusAxis {
    private String id;
    private Integer polarIndex;
    private String type;
    private String name;
    private String nameLocation;
    private List<?> data;
    private NameTextStyle nameTextStyle;
    private NameTruncate nameTruncate;
    private Boolean inverse;
    private Object boundaryGap;
    private FuncStr min;
    private FuncStr max;
    private Boolean scale;
    private Integer splitNumber;
    private Integer minInterval;
    private Integer maxInterval;
    private Integer interval;
    private Integer logBase;
    private Boolean silent;
    private Boolean triggerEvent;
    private AxisLine axisLine;
    private SplitLine splitLine;
    private SplitArea splitArea;
    private AxisPointer axisPointer;
}
