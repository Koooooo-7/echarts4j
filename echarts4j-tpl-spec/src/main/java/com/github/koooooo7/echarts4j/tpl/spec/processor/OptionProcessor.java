package com.github.koooooo7.echarts4j.tpl.spec.processor;

import java.util.function.Predicate;

public class OptionProcessor implements Processor {
    private static final OptionProcessor INSTANCE = new OptionProcessor();

    @Override
    public ProcessorContext apply(ProcessorContext processorContext) {
        return new TitleProcessor()
                .andThen(new ToolboxProcessor())
                .andThen(new NameProcessor())
                .apply(processorContext);
    }

    @Override
    public void process(ProcessorContext context) {

    }

    @Override
    public Predicate<ProcessorContext> matcher() {
        return ctx -> true;
    }

    public static OptionProcessor getINSTANCE() {
        return INSTANCE;
    }
}
