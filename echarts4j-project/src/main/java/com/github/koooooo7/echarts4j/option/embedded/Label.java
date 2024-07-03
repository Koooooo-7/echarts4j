package com.github.koooooo7.echarts4j.option.embedded;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Label {
    private Boolean show;
    private FuncStr position;
    private Integer distance;
    private FuncStr rotate;
    private List<Integer> offset;
    private Integer minMargin;
    private FuncStr formatter;
    private Integer fontSize;
    private ItemStyle itemStyle;
    private LineStyle lineStyle;

}
