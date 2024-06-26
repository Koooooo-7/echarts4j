package com.github.koooooo7.echarts4j.render;

import com.github.koooooo7.echarts4j.util.RendersLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RenderProvider {
    private static final Render render;

    static {
        render = RendersLoader.loadRender();
    }

    public static Render get() {
        return render;
    }

}
