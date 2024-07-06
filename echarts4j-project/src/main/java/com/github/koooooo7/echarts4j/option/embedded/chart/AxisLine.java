package com.github.koooooo7.echarts4j.option.embedded.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.embedded.series.ItemStyle;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope(EmbedScope.Scope.Chart)
public class AxisLine {
    private Boolean show;
    private Boolean onZero;
    private Integer onZeroAxisIndex;
    private String symbol;
    private FuncStr symbolSize;
    private FuncStr symbolOffset;
    private LineStyle lineStyle;


    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LineStyle {
        private String color;
        private Integer width;
        private FuncStr type;
    }


}
