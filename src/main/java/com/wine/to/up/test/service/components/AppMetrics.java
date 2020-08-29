package com.wine.to.up.test.service.components;

import com.wine.to.up.test.service.logging.NotableEvents;
import io.micrometer.core.instrument.Metrics;
import io.prometheus.client.Counter;
import org.springframework.stereotype.Component;

/**
 * This Class expose methods for recording specific metrics
 * It changes metrics of Micrometer and Prometheus simultaneously
 * Micrometer's metrics exposed at /actuator/prometheus
 * Prometheus' metrics exposed at /metrics-prometheus
 *
 */
@Component
public class AppMetrics {
    private static final String EVENTS_TYPE_LABEL = "type";

    private static final String EVENTS = "events_total";
    private static final Counter prometheusEventsCounter = Counter.build()
            .name(EVENTS)
            .help("Amount of occurred events")
            .labelNames(EVENTS_TYPE_LABEL)
            .register();

    public void countEvent(NotableEvents event) {
        Metrics.counter(EVENTS, EVENTS_TYPE_LABEL, event.name()).increment();
        prometheusEventsCounter.labels(event.name()).inc();
    }
}
