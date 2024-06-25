package com.github.koooooo7.echarts4j.option.embed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.koooooo7.echarts4j.option.chart.XAxis;
import com.github.koooooo7.echarts4j.option.chart.YAxis;
import com.github.koooooo7.echarts4j.util.annotation.EmbedScope;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EmbedScope({XAxis.class, YAxis.class})
public class NameTruncate {
}
