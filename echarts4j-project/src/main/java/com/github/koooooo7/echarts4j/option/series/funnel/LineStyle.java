package com.github.koooooo7.echarts4j.option.series.funnel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineStyle {
    private String color;
    private Integer width;
    private FuncStr type;
}
