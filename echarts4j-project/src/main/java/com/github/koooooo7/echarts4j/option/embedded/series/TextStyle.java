package com.github.koooooo7.echarts4j.option.embedded.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope(EmbedScope.Scope.Series)
public class TextStyle {
    private String color;
    private String fontStyle;
    private String fontWeight;
    private Integer fontSize;
}