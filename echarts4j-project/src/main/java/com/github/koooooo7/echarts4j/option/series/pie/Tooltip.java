package com.github.koooooo7.echarts4j.option.series.pie;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope(EmbedScope.Scope.Series)
public class Tooltip {
    private Object position;
    private FuncStr formatter;
    private FuncStr valueFormatter;
    private Integer padding;
    private TextStyle textStyle;
    private AxisPointer axisPointer;
}