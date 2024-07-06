package com.github.koooooo7.echarts4j.util;

import com.github.koooooo7.echarts4j.option.ChartOption;
import com.github.koooooo7.echarts4j.option.chart.Legend;
import com.github.koooooo7.echarts4j.type.FuncStr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class JsonUtilTests {

    @Test
    void ShouldSerializeCorrectly_GivenSingleFuncStr_WhenCallToString() {
        final String legendFormatter = "'Legend {name}'";

        final ChartOption option = ChartOption.builder()
                .legend(Legend.builder()
                        .left("left")
                        .formatter(FuncStr.of(legendFormatter))
                        .build())
                .build();
        final String test = JsonUtil.writeValueAsString(option);
        System.out.println(test);
    }

    @Test
    void ShouldSerializeCorrectly_GivenLotsFuncStr_WhenCallToString() {
        final Mock mock = new Mock();

        mock.setBar(FuncStr.ofStr("bar"));
        mock.setFoo(FuncStr.of("foo"));
        mock.setStr("str");
        final String actual = JsonUtil.writeValueAsString(mock);
        Assertions.assertEquals("{\"foo\":foo,\"bar\":'bar',\"str\":\"str\"}", actual);
    }

    @Test
    void ShouldNotSerializeCorrectly_GivenCycleRef_WhenCallToString() {
        final clzA clzA = new clzA();
        final clzA clzA2 = new clzA();
        final clzB clzB = new clzB();
        final clzC clzC = new clzC();
        clzA.setRefB(clzB);
        clzA.setA("aaa");
        clzB.setRefC(clzC);
        clzC.setRefA(clzA);
        Assertions.assertThrows(Exception.class, () -> JsonUtil.writeValueAsString(clzA));
        // no ref
        clzC.setRefA(clzA2);
        Assertions.assertDoesNotThrow(() -> JsonUtil.writeValueAsString(clzA));
    }

    static class clzA {
        private String a;
        private clzB refB;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public clzB getRefB() {
            return refB;
        }

        public void setRefB(clzB refB) {
            this.refB = refB;
        }
    }

    static class clzB {
        private clzC refC;

        public clzC getRefC() {
            return refC;
        }

        public void setRefC(clzC refC) {
            this.refC = refC;
        }
    }

    static class clzC {
        private String a;
        private clzA refA;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public clzA getRefA() {
            return refA;
        }

        public void setRefA(clzA refA) {
            this.refA = refA;
        }
    }

    static class Mock {
        FuncStr foo;
        FuncStr bar;
        String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public FuncStr getFoo() {
            return foo;
        }

        public void setFoo(FuncStr foo) {
            this.foo = foo;
        }

        public FuncStr getBar() {
            return bar;
        }

        public void setBar(FuncStr bar) {
            this.bar = bar;
        }
    }

}