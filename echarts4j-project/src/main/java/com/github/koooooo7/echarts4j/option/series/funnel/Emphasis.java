package com.github.koooooo7.echarts4j.option.series.funnel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Emphasis {
    private Label label;
    private ItemStyle itemStyle;
}
