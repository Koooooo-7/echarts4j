package com.github.koooooo7.echarts4j.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.embed.NameTextStyle;
import com.github.koooooo7.echarts4j.option.embed.NameTruncate;
import com.github.koooooo7.echarts4j.type.FuncStr;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class XAxis {
    private String id;
    private Boolean show;
    private Integer gridIndex;
    private Boolean alignTicks;
    private String position;
    private Integer offset;
    private String type;
    private String name;
    private String nameLocation;
    private List<?> data;
    private NameTextStyle nameTextStyle;
    private NameTruncate nameTruncate;
    private Boolean inverse;
    private FuncStr min;
    private FuncStr max;
    private Boolean scale;
    private Integer splitNumber;
    private Integer minInterval;
    private Integer maxInterval;
    private Integer interval;
    private Boolean silent;
    private Boolean triggerEvent;

}
