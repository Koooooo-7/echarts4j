package com.github.koooooo7.echarts4j.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RadarChart extends BaseChart<RadarChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Radar;

    @Data
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RadarDataItem {
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
