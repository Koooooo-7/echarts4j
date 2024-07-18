package com.github.koooooo7.echarts4j.tpl.spec.processor;

import com.github.koooooo7.echarts4j.chart.Chart;
import com.github.koooooo7.echarts4j.option.chart.Toolbox;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jParser;

import java.util.Objects;
import java.util.function.Predicate;

public class ToolboxProcessor implements Processor {

    @Override
    public void process(ProcessorContext context) {
        final Chart<?> chart = context.getChart();
        final Echarts4jParser.ToolboxContext ctx = context.getOptionContext().toolbox();
        Toolbox toolbox = chart.getChartOptions().getToolbox();
        if (Objects.isNull(toolbox)) {
            toolbox = Toolbox.builder().build();
        }

        Toolbox.Feature feature = toolbox.getFeature();
        if (Objects.isNull(feature)) {
            feature = Toolbox.Feature.builder().build();
        }

        for (Echarts4jParser.FeatureContext feat : ctx.feature()) {

            final String text = feat.getText();
            if ("dataView".equalsIgnoreCase(text)) {
                feature.setDataView(Toolbox.DataView.builder().build());
            }

            if ("saveAsImage".equalsIgnoreCase(text)) {
                feature.setSaveAsImage(Toolbox.SaveAsImage.builder().build());
            }

            if ("restore".equalsIgnoreCase(text)) {
                feature.setRestore(Toolbox.Restore.builder().build());
            }

        }

        toolbox.setFeature(feature);
        chart.getChartOptions().setToolbox(toolbox);

    }

    @Override
    public Predicate<ProcessorContext> matcher() {
        return ctx -> Objects.nonNull(ctx.getOptionContext().toolbox());
    }
}
