package com.github.koooooo7.echarts4j.tpl.spec.processor;

import java.util.function.Predicate;

public class NameProcessor implements Processor {

    @Override
    public void process(ProcessorContext context) {

    }

    @Override
    public Predicate<ProcessorContext> matcher() {
        return ctx -> false;
    }
}
