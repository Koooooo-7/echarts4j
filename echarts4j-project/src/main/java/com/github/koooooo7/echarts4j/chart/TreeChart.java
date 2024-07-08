package com.github.koooooo7.echarts4j.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.series.tree.Emphasis;
import com.github.koooooo7.echarts4j.option.series.tree.ItemStyle;
import com.github.koooooo7.echarts4j.option.series.tree.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TreeChart extends BaseChart<TreeChart> {
    @Builder.Default
    private ChartType chartType = ChartType.Bar;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TreeDataItem {
        private String name;
        private Integer value;
        private Label label;
        private ItemStyle itemStyle;
        private Emphasis emphasis;
        private List<TreeDataItem> children;
    }
}
