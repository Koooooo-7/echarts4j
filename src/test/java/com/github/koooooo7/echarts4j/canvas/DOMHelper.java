package com.github.koooooo7.echarts.canvas;

import java.util.function.Supplier;
import java.util.regex.Pattern;

public class DOMHelper {

    private DOMHelper() {
    }

    public static final String SRC = "src";
    public static final String HREF = "href";
    public static final String STYLE = "style";
    public static final String EchartsAssetSelector = "[data-asset-type='java-echarts-echarts-asset']";
    public static final String CustomJSAssetSelector = "[data-asset-type='java-echarts-custom-js-asset']";
    public static final String CustomCssAssetSelector = "[data-asset-type='java-echarts-custom-css-asset']";
    public static final String ContainerSelector = "[data-java-echarts-container-id]>div";
    public static final String ChartScriptSelector = "[data-java-echarts-script-id]";
    public static final String ChartScriptChartIdAttr = "data-java-echarts-script-id";


    public static Supplier<Pattern> patternProvider(String chartRawId) {
        final String optionId = "java_echarts_option_" + chartRawId;
        String regex = optionId + "\\s*=\\s*(\\{.*});?";
        return () -> Pattern.compile(regex);
    }
}
