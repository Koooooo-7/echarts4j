package koy.github.koooooo7.echarts.option.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import koy.github.koooooo7.echarts.chart.ChartType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericSeriesOption implements SeriesOption {
    @Builder.Default
    private String type = ChartType.Generic.getType();
    private String name;
    private Boolean show;
    private Object data;
}
