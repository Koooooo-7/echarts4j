package com.github.koooooo7.echarts4j.render;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.util.annotation.Attention;

import java.io.Writer;

public interface Render {

    // render to any output
    void render(Canvas canvas, Writer writer);

    // The order should be sorted, should not include the same order val
    @Attention("The order can not be the same, otherwise the chain is broken")
    int order();

    // The chained Renders, sort by order
    default void setPrevious(Render render) {
    }


}
