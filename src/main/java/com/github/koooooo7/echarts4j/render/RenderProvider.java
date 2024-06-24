package com.github.koooooo7.echarts.render;

import com.github.koooooo7.echarts.util.RendersLoader;

public class RenderProvider {
    private static final Render render;

    static {
        render = RendersLoader.loadRender();
    }

    public static Render get() {
        return render;
    }

}
