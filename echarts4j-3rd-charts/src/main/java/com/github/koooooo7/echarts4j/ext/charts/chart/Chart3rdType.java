package com.github.koooooo7.echarts4j.ext.charts.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Chart3rdType {
    Liquid("liquidFill"),
    WordCloud("wordCloud"),
    ;
    private final String type;
}
