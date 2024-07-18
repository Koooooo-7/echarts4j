package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisualMap {

    private String type;
    private String id;
    private Integer min;
    private Integer max;
    private FuncStr left;
    private List<?> range;
    private Boolean calculable;
    private Boolean realtime;
    private Boolean inverse;
    private String orient;
    private List<String> text;
    private Object inRange;
    private Object nameMap;


    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class InRange {
        private String symbol;
        private FuncStr symbolSize;
        private List<String> color;
    }

}
