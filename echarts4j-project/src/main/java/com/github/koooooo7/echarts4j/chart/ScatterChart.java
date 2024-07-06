package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ScatterChart extends BaseChart<ScatterChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Scatter;

    @Override
    public void postProcessor(Canvas canvas) {
        super.postProcessor(canvas);
        if (Objects.isNull(getChartOptions().getXAxis())) {
            getChartOptions().setXAxis(XAxis.builder().build());
        }
        if (Objects.isNull(getChartOptions().getYAxis())) {
            getChartOptions().setYAxis(YAxis.builder().build());
        }
    }
}
