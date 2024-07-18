package com.github.koooooo7.echarts4j.tpl.spec.processor;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Processor extends Function<ProcessorContext, ProcessorContext> {

    @Override
    default ProcessorContext apply(ProcessorContext processorContext) {
        if (matcher().test(processorContext)) {
            process(processorContext);
        }
        return processorContext;
    }

    void process(ProcessorContext context);

    Predicate<ProcessorContext> matcher();
}
