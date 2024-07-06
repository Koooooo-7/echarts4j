package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.koooooo7.echarts4j.option.embedded.chart.AxisLine;
import com.github.koooooo7.echarts4j.option.embedded.chart.SplitArea;
import com.github.koooooo7.echarts4j.option.embedded.chart.SplitLine;
import com.github.koooooo7.echarts4j.option.embedded.series.AxisPointer;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Radar {
    private String id;
    @JsonProperty("zlevel")
    private Integer zLevel;
    private Integer z;
    private FuncStr center;
    private Object radius;
    private List<IndicatorDataItem> indicator;


    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class IndicatorDataItem {
        private String name;
        private Integer max;
        private Integer min;
        private String color;
    }
}
