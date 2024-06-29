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
public class ListChartSeriesOption extends GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Line.getType();
    private List<?> data;


    @Data
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LineDataItem {
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
