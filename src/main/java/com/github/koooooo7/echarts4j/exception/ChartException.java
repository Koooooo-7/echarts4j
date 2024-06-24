package com.github.koooooo7.echarts.exception;

public class ChartException extends RuntimeException{

    public ChartException(String message) {
        super(message);
    }

    public ChartException(Throwable cause) {
        super(cause);
    }
}
