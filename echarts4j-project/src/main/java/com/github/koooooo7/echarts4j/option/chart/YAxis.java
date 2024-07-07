package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YAxis {
    private String id;
    private Boolean show;
    private Integer gridIndex;
    private Boolean alignTicks;
    private String position;
    private Integer offset;
    private String type;
    private String name;
    private String nameLocation;
    private List<?> data;
    private NameTextStyle nameTextStyle;
    private NameTruncate nameTruncate;
    private Boolean inverse;
    private FuncStr min;
    private FuncStr max;
    private Boolean scale;
    private Integer splitNumber;
    private Integer minInterval;
    private Integer maxInterval;
    private Integer interval;
    private Boolean silent;
    private Boolean triggerEvent;
    private AxisLine axisLine;
    private SplitLine splitLine;
    private SplitArea splitArea;


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

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SplitLine {
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

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SplitArea {
        private Boolean show;
        private String interval;
        private AreaStyle areaStyle;

        @Data
        @Builder
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class AreaStyle {
            private String[] color;
        }
    }

}
