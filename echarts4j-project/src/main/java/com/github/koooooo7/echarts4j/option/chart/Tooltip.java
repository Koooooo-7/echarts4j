package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.embedded.chart.AxisPointer;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tooltip {
    private Boolean show;
    private String trigger;
    private Boolean showContent;
    private Boolean alwaysShowContent;
    private String triggerOn;
    private Integer showDelay;
    private Integer hideDelay;
    private Boolean enterable;
    private String className;
    private Object position;
    private FuncStr formatter;
    private FuncStr valueFormatter;
    private Integer padding;
    private AxisPointer axisPointer;

}
