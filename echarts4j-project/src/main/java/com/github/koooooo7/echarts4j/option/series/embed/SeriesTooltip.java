package com.github.koooooo7.echarts4j.option.series.embed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeriesTooltip {
    private Object position;
    private FuncStr formatter;
    private FuncStr valueFormatter;
    private Integer padding;
}