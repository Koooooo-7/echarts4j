package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.exception.ChartException;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.series.SeriesOption;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.ChartUtil;
import com.github.koooooo7.echarts4j.util.JsonUtil;
import com.github.koooooo7.echarts4j.util.annotation.Attention;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unchecked")
public abstract class BaseChart<T extends Chart<T>> extends Container implements Chart<T> {
    private String chartId;
    private ChartOption options;
    private final List<String> functions = new ArrayList<>();
    private final List<Listener> listeners = new ArrayList<>();
    private boolean async;

    @Override
    public T addJSFunction(FuncStr funcStr) {
        functions.add(JsonUtil.writeValueAsString(funcStr));
        return (T) this;
    }

    @Override
    public T addListener(String eventName, FuncStr handler) {
        listeners.add(new Listener(eventName, JsonUtil.writeValueAsString(handler)));
        return (T) this;
    }

    @Override
    public T addListener(String eventName, FuncStr query, FuncStr handler) {
        listeners.add(new Listener(eventName, JsonUtil.writeValueAsString(query), JsonUtil.writeValueAsString(handler)));
        return (T) this;
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
    public Chart<T> overlap(Chart<?> c) {
        final ChartOption chartOptions = c.getChartOptions();
        final List<SeriesOption> series = chartOptions.getSeries();
        options.getSeries().addAll(series);
        return this;
    }

    /**
     * The postProcessor for chart to make sure all things is set in place.
     * And <strong>it requires no side effect when call it multi times.</strong>
     */
    @Override
    public void postProcessor(Canvas canvas) {
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
                    if (ChartType.Generic == this.getChartType() || ChartType.BASE == this.getChartType()) {
                        throw new ChartException("Please set the chart " +
                                "type in series or use the specific Chart builder rather Generic/Base chart");
                    }
                    s.setType(this.getChartType().getType());
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

        functions.replaceAll(f -> ChartUtil.injectInstance(f, this));
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
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


