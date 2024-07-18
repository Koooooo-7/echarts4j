package com.github.koooooo7.echarts4j.chart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Tooltip;
import com.github.koooooo7.echarts4j.option.chart.VisualMap;
import com.github.koooooo7.echarts4j.option.series.map.Emphasis;
import com.github.koooooo7.echarts4j.option.series.map.Label;
import com.github.koooooo7.echarts4j.option.series.map.MapChartSeries;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class MapChartTests {


    @Test
    void testChart() throws JsonProcessingException {
        final String registerMapScript = "./src/test/resources/data/map/asset/cow.js";

        final Map map = new ObjectMapper().readValue(data, Map.class);
        final Object data = map.get("data");
        Canvas.builder()
                .addCharts(MapChart.builder()
                        .options(ChartOption.builder()
                                .tooltip(Tooltip.builder().build())
                                .visualMap(Collections.singletonList(VisualMap.builder()
                                        .left(FuncStr.ofStr("center"))
                                        .min(5)
                                        .max(100)
                                        .orient("horizontal")
                                        .text(Arrays.asList("", "Price"))
                                        .realtime(true)
                                        .calculable(true)
                                        .inRange(VisualMap.InRange.builder()
                                                .color(Arrays.asList("#dbac00", "#db6e00", "#cf0000"))
                                                .build())


                                        .build()))
                                .build()
                                .addSeries(MapChartSeries.builder()
                                        .map("cow")
                                        .roam(true)
                                        .emphasis(Emphasis.builder()
                                                .label(Label.builder().show(false).build())
                                                .build())

                                        .data((List<?>) data)
                                        .build())
                        )
                        .build()
                        .registerMap(registerMapScript)
                )
                .build()
                .renderTo(new File("cow-map.html"));

    }


    private static final String data = "{\"data\": [\n" +
            "          { \"name\": \"Queue\", \"value\": 15 },\n" +
            "          { \"name\": \"Langue\", \"value\": 35 },\n" +
            "          { \"name\": \"Plat de joue\", \"value\": 15 },\n" +
            "          { \"name\": \"Gros bout de poitrine\", \"value\": 25 },\n" +
            "          { \"name\": \"Jumeau à pot-au-feu\", \"value\": 45 },\n" +
            "          { \"name\": \"Onglet\", \"value\": 85 },\n" +
            "          { \"name\": \"Plat de tranche\", \"value\": 25 },\n" +
            "          { \"name\": \"Araignée\", \"value\": 15 },\n" +
            "          { \"name\": \"Gîte à la noix\", \"value\": 55 },\n" +
            "          { \"name\": \"Bavette d'aloyau\", \"value\": 25 },\n" +
            "          { \"name\": \"Tende de tranche\", \"value\": 65 },\n" +
            "          { \"name\": \"Rond de gîte\", \"value\": 45 },\n" +
            "          { \"name\": \"Bavettede de flanchet\", \"value\": 85 },\n" +
            "          { \"name\": \"Flanchet\", \"value\": 35 },\n" +
            "          { \"name\": \"Hampe\", \"value\": 75 },\n" +
            "          { \"name\": \"Plat de côtes\", \"value\": 65 },\n" +
            "          { \"name\": \"Tendron Milieu de poitrine\", \"value\": 65 },\n" +
            "          { \"name\": \"Macreuse à pot-au-feu\", \"value\": 85 },\n" +
            "          { \"name\": \"Rumsteck\", \"value\": 75 },\n" +
            "          { \"name\": \"Faux-filet\", \"value\": 65 },\n" +
            "          { \"name\": \"Côtes Entrecôtes\", \"value\": 55 },\n" +
            "          { \"name\": \"Basses côtes\", \"value\": 45 },\n" +
            "          { \"name\": \"Collier\", \"value\": 85 },\n" +
            "          { \"name\": \"Jumeau à biftek\", \"value\": 15 },\n" +
            "          { \"name\": \"Paleron\", \"value\": 65 },\n" +
            "          { \"name\": \"Macreuse à bifteck\", \"value\": 45 },\n" +
            "          { \"name\": \"Gîte\", \"value\": 85 },\n" +
            "          { \"name\": \"Aiguillette baronne\", \"value\": 65 },\n" +
            "          { \"name\": \"Filet\", \"value\": 95 }\n" +
            "        ]}";
}