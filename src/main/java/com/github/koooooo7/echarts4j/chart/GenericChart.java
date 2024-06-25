package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.exception.ChartException;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.series.SeriesOption;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.ChartUtil;
import com.github.koooooo7.echarts4j.util.JsonUtil;
import com.github.koooooo7.echarts4j.util.annotation.Attention;
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
    private final List<Listener> listeners = new LinkedList<>();

    public GenericChart addJSFunction(FuncStr funcStr) {
        functions.add(JsonUtil.writeValueAsString(funcStr));
        return this;
    }

    public GenericChart addListener(String eventName, FuncStr handler) {
        listeners.add(new Listener(eventName, JsonUtil.writeValueAsString(handler)));
        return this;
    }

    public GenericChart addListener(String eventName, FuncStr query, FuncStr handler) {
        listeners.add(new Listener(eventName, JsonUtil.writeValueAsString(query), JsonUtil.writeValueAsString(handler)));
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
        injectInstanceToFunctionsIfNecessary();
        injectInstanceToListenersIfNecessary();
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
                    if (ChartType.Generic == chartType) {
                        throw new ChartException("Please set the chart type in series or use the specific Chart builder rather Generic");
                    }
                    s.setType(chartType.getType());
                }
            });
        }
    }

    private void injectInstanceToFunctionsIfNecessary() {
        if (listeners.isEmpty()) {
            return;
        }

        listeners.forEach(l -> l.setHandler(ChartUtil.injectInstance(l.getHandler(), this)));
    }

    private void injectInstanceToListenersIfNecessary() {
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

    @Data
    public static class Listener {
        private String eventName;
        private String query;
        private String handler;

        public Listener(String eventName, String handler) {
            this.eventName = eventName;
            this.handler = handler;
        }

        public Listener(String eventName, String query, String handler) {
            this.eventName = eventName;
            this.query = query;
            this.handler = handler;
        }
    }

}


