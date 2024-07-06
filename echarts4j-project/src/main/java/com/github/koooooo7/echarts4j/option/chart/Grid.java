package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Grid {
    private String id;
    private Boolean show;
    private String left;
    private String top;
    private String right;
    private String bottom;
    private String width;
    private String height;
    private Tooltip tooltip;


    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Tooltip {

    }
}
