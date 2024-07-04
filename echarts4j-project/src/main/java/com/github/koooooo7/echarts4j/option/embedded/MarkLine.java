package com.github.koooooo7.echarts4j.option.embedded;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
