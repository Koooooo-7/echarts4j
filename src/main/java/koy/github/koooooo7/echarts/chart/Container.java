package koy.github.koooooo7.echarts.chart;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder()
public class Container {
    private String containerId;
    @Builder.Default
    private String height = "600px";
    @Builder.Default
    private String width = "900px";

}
