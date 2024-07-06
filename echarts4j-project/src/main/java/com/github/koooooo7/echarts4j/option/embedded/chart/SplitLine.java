package com.github.koooooo7.echarts4j.option.embedded.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SplitLine {
    private Boolean show;
    private String interval;
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
