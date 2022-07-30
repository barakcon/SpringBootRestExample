package com.example.demo.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MyMetrics {

    private MeterRegistry meterRegistry;

    public static Counter counterA;
    public static Counter counterB;
    private Counter counterC;

    public MyMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initCustomCounter();
    }

    private void initCustomCounter() {
        counterA = this.meterRegistry.counter("my.counter", "type", "A");
        counterB = Counter.builder("my.counter")    // 2 - create a counter using the fluent API
                .tag("type", "B")
                .description("just counting")
                .register(meterRegistry);
        this.counterC = this.meterRegistry.counter("my.counter", "type", "C");
    }

    public void doSomeInc() {
        counterC.increment(10);

    }

}
