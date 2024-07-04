package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.embedded.NameTextStyle;
import com.github.koooooo7.echarts4j.option.embedded.NameTruncate;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AngleAxis {
    private String id;
    private Integer polarIndex;
    private Integer startAngle;
    private Integer endAngle;
    private Boolean clockwise;
    private String type;
    private List<?> data;
    private Object boundaryGap;
    private FuncStr min;
    private FuncStr max;
    private Boolean scale;
    private Integer splitNumber;
    private Integer minInterval;
    private Integer maxInterval;
    private Integer interval;
    private Integer logBase;
    private Boolean silent;
    private Boolean triggerEvent;

}
