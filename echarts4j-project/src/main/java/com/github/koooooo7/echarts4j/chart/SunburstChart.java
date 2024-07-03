package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.option.embedded.Emphasis;
import com.github.koooooo7.echarts4j.option.embedded.ItemStyle;
import com.github.koooooo7.echarts4j.option.embedded.Label;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SunburstChart extends BaseChart<SunburstChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Sunburst;

    @Data
    @Builder
    public static class SunburstDataItem {
        private String name;
        private Integer value;
        private Label label;
        private ItemStyle itemStyle;
        private Emphasis emphasis;
        private List<?> children;
    }
}
