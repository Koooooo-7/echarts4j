package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Title {
    private String id;
    private Boolean show;
    private String text;
    private String link;
    private String target;
    private String subtext;
    @JsonProperty("sublink")
    private String subLink;
    @JsonProperty("subtarget")
    private String subTarget;
    private TextStyle textStyle;
    private TextStyle subtextStyle;
    private String textAlign;
    private String left;
    private String top;
    private String right;
    private String bottom;

    @Data
    public static class TextStyle {
        private String color;
        private String fontStyle;
        private String fontWeight;
        private String frontFamily;
        private String frontSize;
        private Integer lineHeight;
        private Integer width;
        private Integer height;


    }
}
