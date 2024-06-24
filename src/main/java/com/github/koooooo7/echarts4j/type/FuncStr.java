package com.github.koooooo7.echarts.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FuncStr {
    private final String val;

    public static FuncStr of(String val) {
        return new FuncStr(val);
    }

}
