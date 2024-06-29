package com.github.koooooo7.echarts4j.helper;

import com.github.koooooo7.echarts4j.exception.ChartException;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * The {@link DataHelper} aims to provide a simple way to build data or any key-val pais json array objects.
 */
public class DataHelper {
    public static final String FIELD_NAME = "name";
    public static final String FIELD_VALUE = "value";
    private final List<String> dataModelFieldNames;
    private final List<Class<?>> dataModelFieldTypes;
    private final List<LinkedHashMap<String, Object>> data = new LinkedList<>();


    DataHelper(Builder builder) {
        dataModelFieldNames = builder.getDataModelFieldNames();
        dataModelFieldTypes = builder.getDataModelFieldTypes();
    }

    public void addData(Consumer<DataHelper> dataAppender) {
        dataAppender.accept(this);
    }

    public DataHelper addData(Object... dataItems) {
        if (dataModelFieldNames.size() != ArrayUtils.getLength(dataItems)) {
            throw new ChartException("data doesn't match the data model");
        }

        final LinkedHashMap<String, Object> item = new LinkedHashMap<>();
        IntStream.range(0, dataModelFieldNames.size())
                .forEach(i -> {
                    final Object it = dataItems[i];
                    final Class<?> dataType = dataModelFieldTypes.get(i);
                    if (!dataType.isInstance(it)) {
                        throw new ChartException("data type doesn't match, " +
                                "expect: " + dataType + " actual:" + it.getClass());
                    }
                    item.put(dataModelFieldNames.get(i), dataItems[i]);
                });
        data.add(item);
        return this;
    }


    public DataHelper addData(LinkedHashMap<String, Object> item) {
        final Object[] dataItems = new Object[item.size()];
        IntStream.range(0, dataModelFieldNames.size())
                .forEach(i -> {
                    final Object it = item.get(dataModelFieldNames.get(i));
                    final Class<?> dataType = dataModelFieldTypes.get(i);
                    if (!dataType.isInstance(it)) {
                        throw new ChartException("data type doesn't match, " +
                                "expect: " + dataType + " actual:" + it.getClass());
                    }
                    dataItems[i] = it;
                });

        return addData(dataItems);
    }

    public List<LinkedHashMap<String, Object>> get() {
        return data;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private final List<String> dataModelFieldNames = new ArrayList<>();
        private final List<Class<?>> dataModelFieldTypes = new ArrayList<>();

        public Builder addNameField() {
            addField(FIELD_NAME, String.class);
            return this;
        }

        public Builder addValueField(Class<?> valueType) {
            addField(FIELD_VALUE, valueType);
            return this;
        }

        public Builder addField(String attrName, Class<?> attrType) {
            if (dataModelFieldNames.contains(attrName)) {
                return this;
            }
            dataModelFieldNames.add(attrName);
            dataModelFieldTypes.add(attrType);
            return this;
        }

        public DataHelper build() {
            if (dataModelFieldNames.isEmpty()) {
                throw new ChartException("Can not init an empty data model");
            }
            return new DataHelper(this);
        }

        List<Class<?>> getDataModelFieldTypes() {
            return dataModelFieldTypes;
        }

        List<String> getDataModelFieldNames() {
            return dataModelFieldNames;
        }
    }

}
