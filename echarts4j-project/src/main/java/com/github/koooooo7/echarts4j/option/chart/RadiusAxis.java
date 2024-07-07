package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.embedded.series.ItemStyle;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RadiusAxis {
    private String id;
    private Integer polarIndex;
    private String type;
    private String name;
    private String nameLocation;
    private List<?> data;
    private NameTextStyle nameTextStyle;
    private NameTruncate nameTruncate;
    private Boolean inverse;
    private Object boundaryGap;
    private FuncStr min;
    private FuncStr max;
    private Boolean scale;
    private Integer splitNumber;
    private Integer minInterval;
    private Integer maxInterval;
    private Integer interval;
    private Integer logBase;
    private Boolean silent;
    private Boolean triggerEvent;
    private AxisLine axisLine;
    private SplitLine splitLine;
    private SplitArea splitArea;
    private AxisPointer axisPointer;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class NameTextStyle {
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class NameTruncate {
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AxisLine {
        private Boolean show;
        private Boolean onZero;
        private Integer onZeroAxisIndex;
        private String symbol;
        private FuncStr symbolSize;
        private FuncStr symbolOffset;
        private AngleAxis.AxisLine.LineStyle lineStyle;


        @Data
        @Builder
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class LineStyle {
            private String color;
            private Integer width;
            private FuncStr type;
        }
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SplitLine {
        private Boolean show;
        private String interval;
        private AngleAxis.SplitLine.LineStyle lineStyle;

        @Data
        @Builder
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class LineStyle {
            private String color;
            private Integer width;
            private FuncStr type;
        }
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SplitArea {
        private Boolean show;
        private String interval;
        private AngleAxis.SplitArea.AreaStyle areaStyle;

        @Data
        @Builder
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class AreaStyle {
            private String[] color;
        }
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AxisPointer {
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


}
