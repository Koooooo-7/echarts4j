package com.github.koooooo7.echarts.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class XAxis {
    @Builder.Default
    private String type = "category";
    private String name;
    private List<?> data;
}
