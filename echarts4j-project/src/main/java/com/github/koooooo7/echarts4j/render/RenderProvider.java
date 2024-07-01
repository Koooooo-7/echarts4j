package com.github.koooooo7.echarts4j.render;

import com.github.koooooo7.echarts4j.util.RendersLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RenderProvider {
    private static final Render render;

    private static final ConcurrentHashMap<Class<? extends Render>, Render> renders = new ConcurrentHashMap<>();

    static {

        render = RendersLoader.loadRenders().stream()
                .peek(r -> renders.put(r.getClass(), r))
                .sorted((r1, r2) -> r2.order() - r1.order())
                .reduce((r1, r2) -> {
                    r2.setPrevious(r1);
                    return r2;
                }).orElseThrow(IllegalStateException::new);
    }

    /**
     * @return the Render chain head
     */
    public static Render get() {
        return render;
    }

    /**
     * @param clz the render clz
     * @param <T> the render type
     * @return the specific render by type
     */
    @SuppressWarnings("unchecked")
    public static <T extends Render> T getRender(Class<T> clz) {
        return (T) renders.get(clz);
    }

}
