package com.github.koooooo7.echarts4j.option.embedded.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Emphasis {
    private Label label;
    private ItemStyle itemStyle;
}
