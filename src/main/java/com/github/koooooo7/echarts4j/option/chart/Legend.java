package com.github.koooooo7.echarts.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts.type.FuncStr;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Legend {
    private String type;
    private Boolean show;
    private FuncStr formatter;
}
