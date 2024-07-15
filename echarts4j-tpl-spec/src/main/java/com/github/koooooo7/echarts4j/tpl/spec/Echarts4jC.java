package com.github.koooooo7.echarts4j.tpl.spec;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.tpl.spec.core.Echarts4jDefListener;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jLexer;
import com.github.koooooo7.echarts4j.tpl.spec.parsing.Echarts4jParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

public class Echarts4jC {
    public static Canvas parse(InputStream is) throws IOException {
        final CharStream cs = CharStreams.fromStream(is);
        final Echarts4jLexer lexer = new Echarts4jLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        final Echarts4jParser echarts4jParser = new Echarts4jParser(tokens);
        final var svc = echarts4jParser.start();
        final ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        final Echarts4jDefListener echarts4jDefListener = new Echarts4jDefListener();
        parseTreeWalker.walk(echarts4jDefListener, svc);
        return echarts4jDefListener.get();

    }
}
