package com.github.koooooo7.echarts4j.ext.charts.chart;

import com.github.koooooo7.echarts4j.chart.BaseChart;
import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.chart.Chart;
import com.github.koooooo7.echarts4j.chart.ChartType;
import com.github.koooooo7.echarts4j.ext.charts.Enhanced3rdChart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Consumer;

/**
 * The WordCloud support from {@link  <a href="https://github.com/ecomfe/echarts-wordcloud"></a>}
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class WordCloudChart extends BaseChart<WordCloudChart> implements Enhanced3rdChart {
    private static final String WORD_CLOUD_ADDITION_ASSET =
            "https://cdn.jsdelivr.net/gh/ecomfe/echarts-wordcloud@master/dist/echarts-wordcloud.min.js";

    @Override
    public ChartType getChartType() {
        return ChartType.Generic;
    }

    @Override
    public String getType() {
        return Chart3rdType.WordCloud.getType();
    }

    @Override
    public void postProcessor(Canvas canvas) {
        super.postProcessor(canvas);
        canvas.asBuilder()
                .appendJSAssets(WORD_CLOUD_ADDITION_ASSET)
                .build();
    }

    @Override
    public Chart<WordCloudChart> overlap(Chart<?> c) {
        throw new UnsupportedOperationException("WordCloud chart currently does not support overlap");
    }
}
