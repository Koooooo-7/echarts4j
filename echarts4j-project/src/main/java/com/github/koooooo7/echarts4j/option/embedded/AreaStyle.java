package com.github.koooooo7.echarts4j.option.embedded;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaStyle {
    private String color;
    private String origin;
}