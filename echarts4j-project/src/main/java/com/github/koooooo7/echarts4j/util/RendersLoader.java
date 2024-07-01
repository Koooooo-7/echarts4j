package com.github.koooooo7.echarts4j.util;

import com.github.koooooo7.echarts4j.render.Render;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RendersLoader {

    public static List<Render> loadRenders() {
        final ServiceLoader<Render> renders = ServiceLoader.load(Render.class);
        final List<Render> col = new ArrayList<>();
        for (Render r : renders) {
            col.add(r);
        }

        return col;
    }
}
