package koy.github.koooooo7.echarts.chart;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class LineChart extends GenericChart{
    @Builder.Default
    private ChartType chartType = ChartType.Line;
}
