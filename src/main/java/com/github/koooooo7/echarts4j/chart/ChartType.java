package com.github.koooooo7.echarts.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
public enum ChartType {
    Generic(StringUtils.EMPTY),
    Line("line"),

    ;
    private final String type;
}
