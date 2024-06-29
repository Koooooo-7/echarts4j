package com.github.koooooo7.echarts4j.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class DataHelperTests {
    @Test
    void shouldCreateCorrectData_WhenBuildTheMode_GivenRelatedData() {
        final DataHelper builder = DataHelper.create()
                .addNameField()
                .addValueField(String.class)
                .addField("mockAField", Integer.class)
                .build();

        for (int i = 0; i < 2; i++) {
            builder.addData("i-" + i, RandomStringUtils.random(3, true, false), ThreadLocalRandom.current().nextInt(100));
        }

        builder.addData(helper -> {
            for (int i = 0; i < 2; i++) {
                helper.addData("i-" + i, RandomStringUtils.random(3, true, false), ThreadLocalRandom.current().nextInt(100));
            }
        });

        final LinkedHashMap<String, Object> dataSet = new LinkedHashMap<>();
        dataSet.put(DataHelper.FIELD_NAME, "mock");
        dataSet.put(DataHelper.FIELD_VALUE, "val");
        dataSet.put("mockAField", ThreadLocalRandom.current().nextInt(100));
        builder.addData(dataSet);

        final List<LinkedHashMap<String, Object>> data = builder.get();
        System.out.println(data);
        Assertions.assertEquals(5, data.size());

    }

}