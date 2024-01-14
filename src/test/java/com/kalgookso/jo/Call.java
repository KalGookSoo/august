package com.kalgookso.jo;

import java.time.Duration;
import java.time.LocalDateTime;

public class Call {
    private final Duration duration;
    private final LocalDateTime from;

    public Call(Duration duration, LocalDateTime from) {
        this.duration = duration;
        this.from = from;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getFrom() {
        return from;
    }
}