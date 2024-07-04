package com.github.koooooo7.echarts4j.option.embedded;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarkArea {
    private Boolean silent;
    private Label label;
    private ItemStyle itemStyle;
    private Emphasis emphasis;
    private Object data;
    private Boolean animation;


    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MarkAreaDataItem {
        private String name;
        private String type;
        private FuncStr coord;
        private Integer x;
        private Integer y;
        private Integer value;
        private ItemStyle itemStyle;
        private Label label;
        private Emphasis emphasis;
        private Boolean animation;
    }
}
