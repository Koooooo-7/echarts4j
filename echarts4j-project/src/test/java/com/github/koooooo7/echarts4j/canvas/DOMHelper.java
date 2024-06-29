package com.github.koooooo7.echarts4j.canvas;

import java.util.function.Supplier;
import java.util.regex.Pattern;

public class DOMHelper {

    private DOMHelper() {
    }

    public static final String SRC = "src";
    public static final String HREF = "href";
    public static final String STYLE = "style";
    public static final String EchartsAssetSelector = "[data-asset-type='echarts4j-echarts-asset']";
    public static final String CustomJSAssetSelector = "[data-asset-type='echarts4j-custom-js-asset']";
    public static final String CustomCssAssetSelector = "[data-asset-type='echarts4j-custom-css-asset']";
    public static final String ContainerSelector = "[data-echarts4j-container-id]>div";
    public static final String ChartScriptSelector = "[data-echarts4j-script-id]";
    public static final String ChartScriptChartIdAttr = "data-echarts4j-script-id";


    public static Supplier<Pattern> patternProvider(String chartRawId) {
        final String optionId = "echarts4j_option_" + chartRawId;
        String regex = optionId + "\\s*=\\s*(\\{.*});?";
        return () -> Pattern.compile(regex);
    }
}
