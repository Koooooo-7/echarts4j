package com.github.koooooo7.echarts4j.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
public enum ChartType {
    Generic(StringUtils.EMPTY),
    Line("line"),
    Bar("bar"),
    Pie("pie"),

    ;
    private final String type;
}
