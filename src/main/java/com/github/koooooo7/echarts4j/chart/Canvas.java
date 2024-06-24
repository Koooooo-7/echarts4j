package com.github.koooooo7.echarts.chart;


import com.github.koooooo7.echarts.exception.ChartException;
import com.github.koooooo7.echarts.exception.RenderException;
import com.github.koooooo7.echarts.render.Render;
import com.github.koooooo7.echarts.render.RenderProvider;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Data
public class Canvas {
    private String title = "java-echarts";
    private String echartsAsset = "https://cdn.jsdelivr.net/npm/echarts@5/dist/echarts.min.js";
    private List<String> appendJsAssets = new ArrayList<>();
    private List<String> appendCssAssets = new ArrayList<>();
    private final Map<String, Chart> charts = new LinkedHashMap<>();

    Canvas() {
    }

    // A handy method to quick render out
    public Canvas renderTo(File file) {
        try (Writer writer = new FileWriter(file)) {
            RenderProvider.get().render(this, writer);
            return this;
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    public Canvas renderTo(Writer writer) {
        RenderProvider.get().render(this, writer);
        return this;
    }

    // one way step
    public Render extractRender() {
        return RenderProvider.get();
    }

    public CanvasBuilder asBuilder() {
        return new CanvasBuilder(this);
    }

    private void registerChart(String chartId, Chart chart) {
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

        public CanvasBuilder appendJSAssets(String... jsAsset) {
            canvas.appendJsAssets.addAll(Arrays.asList(jsAsset));
            return this;
        }

        public CanvasBuilder appendCssAssets(String... jsAsset) {
            canvas.appendCssAssets.addAll(Arrays.asList(jsAsset));
            return this;
        }

        public CanvasBuilder replaceEchartsAsset(String echartsAsset) {
            canvas.setEchartsAsset(StringUtils.trim(echartsAsset));
            return this;
        }

        public CanvasBuilder addCharts(Chart... charts) {
            Arrays.stream(charts).forEach(c -> {
                if (Objects.isNull(c.getChartType())) {
                    throw new ChartException("Can not add a no type chart !");
                }
                c.postProcessor();
                canvas.registerChart(c.getChartId(), c);
            });

            return this;
        }

        @SuppressWarnings("unchecked")
        public <T extends Chart> CanvasBuilder updateChart(String chartId, Consumer<Optional<T>> chartModifier) {
            chartModifier.accept(Optional.ofNullable((T) (canvas.getCharts().get(chartId))));
            return this;
        }

        public Canvas build() {
            return canvas;
        }

    }


}
