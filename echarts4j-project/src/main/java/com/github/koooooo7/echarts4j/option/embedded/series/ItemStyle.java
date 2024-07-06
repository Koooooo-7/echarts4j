package com.github.koooooo7.echarts4j.option.embedded.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope(EmbedScope.Scope.Series)
public class ItemStyle {
    private FuncStr color;
    private FuncStr color0;
    private FuncStr borderColor;
    private FuncStr borderColor0;
}
