package com.github.koooooo7.echarts.util;

import com.github.koooooo7.echarts.render.Render;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RendersLoader {

    public static Render loadRender() {
        final ServiceLoader<Render> renders = ServiceLoader.load(Render.class);
        final List<Render> col = new ArrayList<>();
        for (Render r : renders) {
            col.add(r);
        }

        return col.stream()
                .sorted((r1, r2) -> r2.order() - r1.order())
                .reduce((r1, r2) -> {
                    r2.setPrevious(r1);
                    return r2;
                }).orElseThrow(IllegalStateException::new);
    }
}
