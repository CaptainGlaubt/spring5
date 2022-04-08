package se.lernholt.actuator.metrics;

import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@RepositoryEventHandler(Object.class)
public class CustomMetricsPublisher {

    private final MeterRegistry meterRegistry;

    @HandleAfterSave
    public void onAfterSave(Object object) {
        meterRegistry.counter("object", object.toString()).increment();
    }
}
