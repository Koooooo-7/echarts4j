package com.github.koooooo7.echarts4j.option.series.bar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope(EmbedScope.Scope.Series)
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
