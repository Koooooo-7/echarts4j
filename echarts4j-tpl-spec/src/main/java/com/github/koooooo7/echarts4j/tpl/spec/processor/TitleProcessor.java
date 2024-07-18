package com.github.koooooo7.echarts4j.tpl.spec.processor;

import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jParser;

import java.util.Objects;
import java.util.function.Predicate;

public class TitleProcessor implements Processor {

    @Override
    public void process(ProcessorContext context) {
        final Echarts4jParser.TitleContext title = context.getOptionContext().title();
        context.getChart().getChartOptions().setTitle(Title.builder().text(title.ID().getText()).build());
    }

    @Override
    public Predicate<ProcessorContext> matcher() {
        return ctx -> Objects.nonNull(ctx.getOptionContext().title());
    }
}
