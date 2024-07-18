package com.github.koooooo7.echarts4j.chart;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class MapChart extends BaseChart<MapChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Map;
    private final Set<String> registerMapScriptResources = new HashSet<>();


    public MapChart registerMap(String registerMapScript) {
        registerMapScriptResources.add(registerMapScript);
        return this;
    }

    @Override
    public void postProcessor(Canvas canvas) {
        canvas.asBuilder()
                .appendJSAssets(registerMapScriptResources.toArray(new String[0]))
                .build();
        super.postProcessor(canvas);
    }
}
