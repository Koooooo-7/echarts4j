package com.github.koooooo7.echarts4j.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// A comment annotate for note the options embed scope
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface EmbedScope {

    Scope value();

    enum Scope {
        Chart,
        Series;
    }
}
