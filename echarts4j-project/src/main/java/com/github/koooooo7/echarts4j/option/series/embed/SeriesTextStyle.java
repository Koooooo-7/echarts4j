package com.github.koooooo7.echarts4j.option.series.embed;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeriesTextStyle {
    private String color;
    private String fontStyle;
    private String fontWeight;
    private Integer fontSize;
}