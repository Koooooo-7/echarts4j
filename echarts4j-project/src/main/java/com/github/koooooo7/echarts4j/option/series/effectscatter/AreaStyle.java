package com.github.koooooo7.echarts4j.option.series.effectscatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope(EmbedScope.Scope.Series)
public class AreaStyle {
    private String color;
    private String origin;
}
