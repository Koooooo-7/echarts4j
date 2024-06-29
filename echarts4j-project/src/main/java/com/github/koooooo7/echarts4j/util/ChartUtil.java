package com.github.koooooo7.echarts4j.util;

import com.github.koooooo7.echarts4j.chart.Chart;
import com.github.koooooo7.echarts4j.chart.GenericChart;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChartUtil {
    private static final String INSTANCE_PREFIX = "echarts4j_";
    private static final String INSTANCE_PLACEHOLDER = "%MY_ECHARTS%";

    public static String injectInstance(String funcStr, Chart chart) {
        return funcStr.replaceAll(INSTANCE_PLACEHOLDER, INSTANCE_PREFIX + chart.getChartId());
    }

    public static String generateChartId() {
        return RandomStringUtils.random(10, true, true);
    }
}
