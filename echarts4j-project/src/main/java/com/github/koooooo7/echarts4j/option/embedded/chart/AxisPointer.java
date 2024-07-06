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
public class AxisPointer {
    private String type;
    private String axis;
    private Boolean snap;
    private Integer z;
    private Label label;
    private LineStyle lineStyle;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Label {
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

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LineStyle {
        private String color;
        private Integer width;
        private FuncStr type;
    }


}