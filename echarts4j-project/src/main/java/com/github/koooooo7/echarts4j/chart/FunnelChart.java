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
public class FunnelChart extends BaseChart<FunnelChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Funnel;


    @Data
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FunnelDataItem {
        private String name;
        private Integer value;
    }
}
