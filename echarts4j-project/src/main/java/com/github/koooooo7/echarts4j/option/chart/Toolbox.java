package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Toolbox {
    private String id;
    private Boolean show;
    private String orient;
    private Integer itemSize;
    private Integer itemGap;
    private Boolean showTitle;
    private Feature feature;
    private String left;
    private String top;
    private String right;
    private String bottom;
    private String width;
    private String height;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Feature {
        private SaveAsImage saveAsImage;
        private Restore restore;
        private DataView dataView;

    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SaveAsImage {
        private String type;
        private String name;
        private Boolean show;
        private String title;
        private String icon;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Restore {
        private Boolean show;
        private String title;
        private String icon;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DataView {
        private Boolean show;
        private String title;
        private String icon;
        private Boolean readOnly;
    }


}
