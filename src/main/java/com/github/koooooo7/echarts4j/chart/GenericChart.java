package com.github.koooooo7.echarts.chart;

import com.github.koooooo7.echarts.option.ChartOption;
import com.github.koooooo7.echarts.option.series.SeriesOption;
import com.github.koooooo7.echarts.type.FuncStr;
import com.github.koooooo7.echarts.util.ChartUtil;
import com.github.koooooo7.echarts.util.JsonUtil;
import com.github.koooooo7.echarts.util.annotation.Attention;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class GenericChart extends Container implements Chart {
    @Builder.Default
    private ChartType chartType = ChartType.Generic;
    private String chartId;
    private ChartOption options;
    private final List<String> functions = new LinkedList<>();

    public GenericChart addJSFunctions(FuncStr funcStr) {
        functions.add(JsonUtil.writeValueAsString(funcStr));
        return this;
    }

    @Attention("Exposed for template and options expert")
    @Override
    public String getOptions() {
        return JsonUtil.writeValueAsString(options);
    }

    @Override
    public ChartOption getChartOptions() {
        return this.options;
    }

    @Override
    public Chart overlap(Chart c) {
        final ChartOption chartOptions = c.getChartOptions();
        final List<SeriesOption> series = chartOptions.getSeries();
        options.getSeries().addAll(series);
        return this;
    }

    @Override
    public void postProcessor() {
        configChartIdIfNecessary();
        configChartTypeOnSeriesIfNecessary();
        injectInstanceToFuncStrIfNecessary();
    }

    private void configChartIdIfNecessary() {
        if (StringUtils.isEmpty(chartId)) {
            chartId = ChartUtil.generateChartId();
        }
        this.setContainerId(this.chartId);
    }

    private void configChartTypeOnSeriesIfNecessary() {
        if (Objects.nonNull(options)) {
            options.getSeries().forEach(s -> {
                if (StringUtils.isEmpty(s.getType())) {
                    s.setType(chartType.getType());
                }
            });
        }
    }

    private void injectInstanceToFuncStrIfNecessary() {
        if (functions.isEmpty()) {
            return;
        }

        final List<String> tmp = functions
                .stream()
                .map(f -> ChartUtil.injectInstance(f, this))
                .collect(Collectors.toList());
        functions.clear();
        functions.addAll(tmp);
    }

}


