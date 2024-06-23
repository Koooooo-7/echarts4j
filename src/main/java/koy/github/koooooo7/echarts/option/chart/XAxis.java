package koy.github.koooooo7.echarts.option.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class XAxis {
    @Builder.Default
    private String type = "category";
    private Object data;
}
