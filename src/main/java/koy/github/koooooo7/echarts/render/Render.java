package koy.github.koooooo7.echarts.render;

import koy.github.koooooo7.echarts.chart.Canvas;
import koy.github.koooooo7.echarts.util.annotation.Attention;

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
