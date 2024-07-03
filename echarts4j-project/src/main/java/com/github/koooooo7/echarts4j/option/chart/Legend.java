package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Legend {
    private String type;
    private String id;
    private Boolean show;
    private FuncStr formatter;
    private String left;
    private String top;
    private String right;
    private String bottom;
    private String width;
    private String height;
    private String orient;
    private String align;
    private Object padding;
    private Integer itemGap;
    private Integer itemWidth;
    private Integer itemHeight;
    private ItemStyle itemStyle;
    private LineStyle lineStyle;
    private TextStyle textStyle;
    private String icon;
    private List<?> data;


    @Builder
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ItemStyle {
        private FuncStr color;

    }

    @Builder
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LineStyle {
        private String color;
        private Integer width;
        private FuncStr type;

    }

    @Builder
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TextStyle {

    }


}
