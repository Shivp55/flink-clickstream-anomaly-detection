package com.shaishav.flink.util;

import java.io.Serializable;

public class SimpleAnomalyDetector implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long upperBound;

    public SimpleAnomalyDetector(long upperBound) {
        this.upperBound = upperBound;
    }

    public boolean isAnomalous(long value) {
        return value > upperBound;
    }

    public static SimpleAnomalyDetector defaultForClickstream() {
        // e.g. 200 events per user per minute
        return new SimpleAnomalyDetector(200L);
    }
}
