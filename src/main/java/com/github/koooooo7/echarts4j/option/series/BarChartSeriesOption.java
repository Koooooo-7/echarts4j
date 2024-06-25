package com.github.koooooo7.echarts4j.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.chart.ChartType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class BarChartSeriesOption extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Bar.getType();
    private List<?> data;
    private Boolean roundCap;
    private Boolean realtimeSort;
    private String barWidth;
    private String barMaxWidth;
    private String barMinWidth;
    private String barMinHeight;
    private String barMinAngle;
    private String barGap;
    private String barCategoryGap;


    @Data
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BarDataItem {
        private String name;
        private Integer value;
        private String groupId;
        private String childGroupId;
        private String symbol;
        private Object symbolSize;
        private Integer symbolRotate;
        private Boolean symbolKeepAspect;
        private List<?> symbolOffset;

    }

}
