package com.github.koooooo7.echarts4j.type;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * The {@link FuncStr} wrappers the val which would be pure raw context at rendering out. e.g.
 * A pure Number: FuncStr.of("1")  => 1
 * A pure String: FuncStr.of("'code'")  => 'code'
 * A pure Array: FunStr.of("[0,'24%']") => [0, '24%']
 * A pure Content: FuncStr.of("plain") => plain
 * A pure Function: FunStr.of("() => console.log('echarts4j!')") => () => console.log('echarts4j')
 */
@Getter
@AllArgsConstructor
public class FuncStr {
    private final String val;

    // pure content
    public static FuncStr of(String val) {
        return new FuncStr(val);
    }

    // pure content
    public static FuncStr of(Boolean val) {
        return new FuncStr(String.valueOf(val));
    }

    // pure content
    public static FuncStr of(Integer val) {
        return new FuncStr(String.valueOf(val));
    }

    // render as string => 'MyString'
    public static FuncStr ofStr(String val) {
        return new FuncStr("'" + val + "'");
    }

}
