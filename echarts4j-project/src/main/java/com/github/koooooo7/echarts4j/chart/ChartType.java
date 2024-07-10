package com.github.koooooo7.echarts4j.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
public enum ChartType {
    /**
     * The base abstract chart definition.
     */
    BASE(StringUtils.EMPTY),
    /**
     * The generic chart with full customize.
     */
    Generic(StringUtils.EMPTY),
    Line("line"),
    Bar("bar"),
    Pie("pie"),
    Scatter("scatter"),
    EffectScatter("effectScatter"),
    Radar("radar"),
    Sunburst("sunburst"),
    Candlestick("candlestick"),
    Tree("tree"),
    Funnel("funnel"),
    ;

    private final String type;
}
