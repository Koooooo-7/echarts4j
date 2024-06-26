package com.github.koooooo7.echarts4j.chart;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PieChart extends BaseChart<PieChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Pie;
}
