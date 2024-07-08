package com.github.koooooo7.echarts4j.option.series.bar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope(EmbedScope.Scope.Series)
public class MarkLine {
    private Boolean silent;
    private FuncStr symbol;
    private FuncStr symbolSize;
    private Integer percision;
    private Label label;
    private LineStyle lineStyle;
    private Emphasis emphasis;
    private List<?> data;
    private Boolean animation;


    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MarkLineDataItem {
        private String name;
        private String type;
        private FuncStr coord;
        private Integer x;
        private Integer y;
        private Integer value;
        private String symbol;
        private FuncStr symbolSize;
        private Integer symbolRotate;
        private FuncStr symbolOffset;
        private ItemStyle itemStyle;
        private Label label;
        private Emphasis emphasis;
        private Boolean animation;
    }
}
