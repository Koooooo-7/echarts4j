package com.github.koooooo7.echarts4j.option.embedded;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope(EmbedScope.Scope.Series)
public class AreaStyle {
    private String color;
    private String origin;
}
