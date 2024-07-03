package com.github.koooooo7.echarts4j.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LineChart extends BaseChart<LineChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Line;

    @Override
    public void postProcessor() {
        super.postProcessor();
        if (Objects.isNull(getChartOptions().getXAxis())) {
            getChartOptions().setXAxis(XAxis.builder().build());
        }
        if (Objects.isNull(getChartOptions().getYAxis())) {
            getChartOptions().setYAxis(YAxis.builder().build());
        }
    }

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
