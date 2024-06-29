package com.github.koooooo7.echarts4j.render;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.exception.RenderException;
import com.github.koooooo7.echarts4j.util.annotation.Attention;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public interface Render {

    /**
     * A simple render for Canvas with default output located {@code new File("echarts4j.html") }.
     *
     * @param canvas The {@link Canvas} instance
     */
    default void render(Canvas canvas) {
        render(canvas, new File("echarts4j.html"));
    }

    default void render(Canvas canvas, File file) {
        try (Writer writer = new FileWriter(file)) {
            render(canvas, writer);
        } catch (IOException e) {
            throw new RenderException(e);
        }
    }

    /**
     * @param canvas The {@link Canvas} instance
     * @param writer The Writer instance, <strong>Note:</strong> it needs to manually close by user.
     */
    @Attention("render to any output, won't close the stream")
    void render(Canvas canvas, Writer writer);

    /**
     * The Order to build Render Chain, <strong>Note:</strong> all {@link Render}s should not have same order.
     *
     * @return the Render order
     */
    @Attention("The order can not be the same, otherwise the chain is broken")
    int order();

    /**
     * Provide the next lower {@link Render} from the chain.
     */
    default void setPrevious(Render render) {
    }


}
