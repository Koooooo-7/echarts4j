package com.github.koooooo7.echarts4j.render;

import com.github.koooooo7.echarts4j.util.RendersLoader;

public class RenderProvider {
    private static final Render render;

    static {
        render = RendersLoader.loadRender();
    }

    public static Render get() {
        return render;
    }

}
