package koy.github.koooooo7.echarts.util;

import koy.github.koooooo7.echarts.chart.GenericChart;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChartUtil {
    private static final String INSTANCE_PREFIX = "java_echarts_";
    private static final String INSTANCE_PLACEHOLDER = "%MY_ECHARTS%";

    public static String injectInstance(String funcStr, GenericChart chart) {
        return funcStr.replaceAll(INSTANCE_PLACEHOLDER, INSTANCE_PREFIX + chart.getChartId());
    }

    public static String generateChartId() {
        return RandomStringUtils.random(10, true, true);
    }
}
