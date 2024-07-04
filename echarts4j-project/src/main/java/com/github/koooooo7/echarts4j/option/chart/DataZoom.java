package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class DataZoom {

    @Data
    @Builder
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class InsideDataZoom extends DataZoom {
        private String id;
        @Builder.Default
        private String type = "inside";
        private Integer start;
        private Integer end;
        private FuncStr xAxisIndex;
        private FuncStr yAxisIndex;

    }

    @Data
    @Builder
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SliderDataZoom extends DataZoom {
        private String id;
        @Builder.Default
        private String type = "slider";
        private Boolean show;
        private Integer start;
        private Integer end;
        private FuncStr xAxisIndex;
        private FuncStr yAxisIndex;
    }

}
