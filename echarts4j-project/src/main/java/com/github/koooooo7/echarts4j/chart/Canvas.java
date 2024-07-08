package com.github.koooooo7.echarts4j.chart;


import com.github.koooooo7.echarts4j.exception.ChartException;
import com.github.koooooo7.echarts4j.render.Render;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Data
public class Canvas {
    private String layout = "center";
    private String title = "echarts4j";
    private String echartsAsset = "https://cdn.jsdelivr.net/npm/echarts@5/dist/echarts.min.js";
    private List<String> appendJsAssets = new ArrayList<>();
    private List<String> appendCssAssets = new ArrayList<>();
    private final Map<String, Chart<?>> charts = new LinkedHashMap<>();

    Canvas() {
    }

    /**
     * A handy method to quick render out charts to default canvas.
     *
     * @return the {@link Canvas} instance for further operations.
     */
    public Canvas render() {
        RenderProvider.get().render(this);
        return this;
    }

    /**
     * A handy method to quick render out charts.
     *
     * @param file the {@link File} locate.
     * @return the {@link Canvas} instance for further operations.
     */
    public Canvas renderTo(File file) {
        RenderProvider.get().render(this, file);
        return this;
    }

    /**
     * A handy method to quick render out charts to different output.
     *
     * @param writer the {@link Writer}, such as {@link java.io.FileWriter}, {@link java.io.StringWriter}.
     * @return the {@link Canvas} instance for further operations.
     */
    public Canvas renderTo(Writer writer) {
        RenderProvider.get().render(this, writer);
        return this;
    }


    /**
     * A one way step to extract the {@link Render}, same to use {@see RenderProvider#get()}.
     *
     * @return the {@link Render} instance head of the render chain.
     */
    public Render extractRender() {
        return RenderProvider.get();
    }

    public CanvasBuilder asBuilder() {
        return new CanvasBuilder(this);
    }

    private void addJsAsset(String jsAsset) {
        if (appendJsAssets.contains(jsAsset)) {
            return;
        }

        appendJsAssets.add(jsAsset);

    }

    private void addCssAsset(String cssAsset) {
        if (appendCssAssets.contains(cssAsset)) {
            return;
        }

        appendCssAssets.add(cssAsset);

    }

    private void registerChart(String chartId, Chart<?> chart) {
        charts.putIfAbsent(chartId, chart);
    }

    public static CanvasBuilder builder() {
        final Canvas instance = new Canvas();
        return new CanvasBuilder(instance);
    }

    public static class CanvasBuilder {
        public CanvasBuilder(Canvas canvas) {
            this.canvas = canvas;
        }

        private final Canvas canvas;

        public CanvasBuilder title(String title) {
            canvas.setTitle(title);
            return this;
        }

        public CanvasBuilder layout(CanvasLayout layout) {
            canvas.setLayout(layout.getLayout());
            return this;
        }

        public CanvasBuilder appendJSAssets(String... jsAsset) {
            Arrays.stream(jsAsset).forEach(canvas::addJsAsset);
            return this;
        }

        public CanvasBuilder appendCssAssets(String... cssAsset) {
            Arrays.stream(cssAsset).forEach(canvas::addCssAsset);
            return this;
        }

        public CanvasBuilder replaceEchartsAsset(String echartsAsset) {
            canvas.setEchartsAsset(StringUtils.trim(echartsAsset));
            return this;
        }

        public CanvasBuilder addCharts(Chart<?>... charts) {
            Arrays.stream(charts).forEach(c -> {
                c.postProcessor(canvas);
                if (Objects.isNull(c.getChartType())) {
                    throw new ChartException("Can not add a no type chart !");
                }
                canvas.registerChart(c.getChartId(), c);
            });

            return this;
        }

        /**
         * @param chartId       the chartId which user preset on {@link Chart} when addChart.
         * @param chartModifier the chartModifier to update the Chart.
         * @return the {@link CanvasBuilder} to do further operations.
         */
        public CanvasBuilder updateChart(String chartId, Consumer<Optional<Chart<?>>> chartModifier) {
            chartModifier.accept(Optional.ofNullable((canvas.getCharts().get(chartId))));
            Optional.ofNullable(canvas.getCharts().get(chartId)).ifPresent(c -> c.postProcessor(canvas));
            return this;
        }

        /**
         * @param chartsModifier the chartsModifier to update all the added Charts.
         * @return the {@link CanvasBuilder} to do further operations.
         */
        public CanvasBuilder updateCharts(BiConsumer<String, Chart<?>> chartsModifier) {
            canvas.getCharts().forEach((id, chart) -> {
                chartsModifier.accept(id, chart);
                chart.postProcessor(canvas);
            });
            return this;
        }

        public Canvas build() {
            return canvas;
        }

    }

    @AllArgsConstructor
    @Getter
    public enum CanvasLayout {
        NONE("none"),
        Center("center"),
        ;
        private final String layout;
    }


}
