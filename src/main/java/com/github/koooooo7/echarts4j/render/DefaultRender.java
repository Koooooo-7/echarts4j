package com.github.koooooo7.echarts.render;

import freemarker.template.Template;
import com.github.koooooo7.echarts.chart.Canvas;
import com.github.koooooo7.echarts.exception.RenderException;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class DefaultRender implements Render {
    private static final String CANVAS_KEY = "canvas";
    private static final String CHARTS_KEY = "charts";

    @Override
    public void render(Canvas canvas, Writer writer) {
        final Template template;
        try {
            Map<String, Object> parts = new HashMap<>();
            parts.put(CANVAS_KEY, canvas);
            parts.put(CHARTS_KEY, canvas.getCharts());
            template = TemplateManager.getTemplate();
            template.process(parts, writer);
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    @Override
    public int order() {
        return Integer.MAX_VALUE;
    }

}
