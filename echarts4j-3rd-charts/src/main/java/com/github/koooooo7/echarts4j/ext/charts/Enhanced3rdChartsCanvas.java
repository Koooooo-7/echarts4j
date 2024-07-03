package com.github.koooooo7.echarts4j.ext.charts;

import com.github.koooooo7.echarts4j.chart.Canvas;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Enhanced3rdChartsCanvas {

    public static Canvas.CanvasBuilder builder() {
        return box3rdChartsSupport(Canvas.builder());
    }

    public static Canvas.CanvasBuilder box3rdChartsSupport(Canvas.CanvasBuilder canvasBuilder) {

        if (Enhancer.isEnhanced(canvasBuilder.getClass())) {
            return canvasBuilder;
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Canvas.CanvasBuilder.class);
        enhancer.setCallback(new PostEnhancerProcessInterceptor(canvasBuilder));
        return (Canvas.CanvasBuilder) enhancer.create(new Class[]{Canvas.class}, new Object[]{canvasBuilder.build()});

    }

    private static class PostEnhancerProcessInterceptor implements MethodInterceptor {
        private static final Predicate<Method> canvasBuildMethod = method -> method.getName().contains("build");
        private final Canvas.CanvasBuilder target;

        public PostEnhancerProcessInterceptor(Canvas.CanvasBuilder target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (canvasBuildMethod.test(method)) {
                final Canvas canvas = target.build();
                canvas.getCharts().values().stream()
                        .filter(c -> c instanceof Enhanced3rdChart)
                        .map(it -> (Enhanced3rdChart) it)
                        .forEach(chart -> {
                            chart.canvasPostProcessor().accept(canvas);
                        });
                return canvas;
            }

            method.invoke(target, args);
            return obj;
        }
    }
}
