package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PieChart extends GenericChart {
    @Builder.Default
    private ChartType chartType = ChartType.Line;

    public PieChart addJSFunction(FuncStr funcStr) {
        super.addJSFunction(funcStr);
        return this;
    }

    public PieChart addListener(String eventName, FuncStr handler) {
        super.addListener(eventName, handler);
        return this;
    }

    public PieChart addListener(String eventName, FuncStr query, FuncStr handler) {
        super.addListener(eventName, query, handler);
        return this;
    }
}
