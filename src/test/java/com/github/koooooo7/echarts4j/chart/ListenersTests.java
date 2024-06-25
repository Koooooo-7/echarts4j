package com.github.koooooo7.echarts4j.chart;

import com.github.koooooo7.echarts4j.helper.DataHelper;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.option.chart.Title;
import com.github.koooooo7.echarts4j.option.series.PieChartSeriesOption;
import com.github.koooooo7.echarts4j.render.Render;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

public class ListenersTests {

    private static final String actionWithEchartsInstance =
            "        let currentIndex = -1;\n" +
                    "        setInterval(function() {\n" +
                    "          const myChart = %MY_ECHARTS%;\n" +
                    "          var dataLen = myChart.getOption().series[0].data.length;\n" +
                    "          myChart.dispatchAction({\n" +
                    "            type: 'downplay',\n" +
                    "            seriesIndex: 0,\n" +
                    "            dataIndex: currentIndex\n" +
                    "          });\n" +
                    "          currentIndex = (currentIndex + 1) % dataLen;\n" +
                    "          myChart.dispatchAction({\n" +
                    "            type: 'highlight',\n" +
                    "            seriesIndex: 0,\n" +
                    "            dataIndex: currentIndex\n" +
                    "          });\n" +
                    "          myChart.dispatchAction({\n" +
                    "            type: 'showTip',\n" +
                    "            seriesIndex: 0,\n" +
                    "            dataIndex: currentIndex\n" +
                    "          });\n" +
                    "        }, 1000);\n";


    private static final String clickAlterFunc = " (params) => alert(params.name)";
    private static final String legendClickAlterFunc = " (params) => alert(\"Legend clicked:\"+params.name)";
    private static final String mouseMoveAlterFunc = " (params) => alert(\"I'm the 135!\")";

    @Test
    void shouldRenderCorrectly_WhenRenderThePieCharts_GivenRelatedConfigs() {

        final String chartTitle = "Dynamic Pie";
        final String seriesName = "Access Source";

        final List<String> legendData = Arrays.asList(
                "Direct Access",
                "Email Marketing",
                "Affiliate Ads",
                "Video Ads",
                "Search Engines"
        );

        final DataHelper dataHelper = DataHelper.create()
                .addValueField(Integer.class)
                .addNameField()
                .build()
                .addData(335, "Direct Access")
                .addData(310, "Email Marketing")
                .addData(234, "Affiliate Ads")
                .addData(135, "Video Ads")
                .addData(1548, "Search Engines");

        final List<String> center = Arrays.asList("50%", "60%");
        final PieChart p = PieChart.builder()
                .options(ChartOption.builder()
                        .animation(false)
                        .title(Title.builder()
                                .text(chartTitle)
                                .right("20")
                                .build())
                        .legend(Legend.builder()
                                .orient("vertical")
                                .left("left")
                                .data(legendData)
                                .build())
                        .build()
                        .addSeries(PieChartSeriesOption.builder()
                                .name(seriesName)
                                .radius("55%")
                                .center(center)
                                .data(dataHelper.get())
                                .build())
                )
                .build()
                .addJSFunction(FuncStr.of(actionWithEchartsInstance))
                .addListener("'click'", FuncStr.of(clickAlterFunc))
                .addListener("'legendselectchanged'", FuncStr.of(legendClickAlterFunc))
                .addListener("'mousemove'", FuncStr.of("{ dataIndex: 3 }"), FuncStr.of(mouseMoveAlterFunc));


        try (StringWriter writer = new StringWriter()) {
            final Canvas cvs = Canvas.builder()
                    .addCharts(p)
                    .build();
            final Render render = RenderProvider.get();
            render.render(cvs, writer);
            render.render(cvs, new FileWriter("dynamic-pie.html"));
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
