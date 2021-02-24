package com.arm.api.hero.util;

import java.time.Duration;
import java.time.Instant;

public class OperationTimer {
    private String description;
    private Instant startInstant;
    private Instant finishInstant;

    private OperationTimer() {
    }

    public static OperationTimer start() {
        OperationTimer operationTimer = new OperationTimer();
        operationTimer.setStartInstant(Instant.now());
        return operationTimer;
    }

    public static OperationTimer start(String description) {
        OperationTimer operationTimer = start();
        operationTimer.setDescription(description);
        return operationTimer;
    }

    public long finish() {
        this.setFinishInstant(Instant.now());
        return duration();
    }

    public long duration() {
        if (finishInstant == null) {
            this.setFinishInstant(Instant.now());
        }

        return Duration.between(startInstant, finishInstant).toMillis();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getStartInstant() {
        return startInstant;
    }

    public void setStartInstant(Instant startInstant) {
        this.startInstant = startInstant;
    }

    public Instant getFinishInstant() {
        return finishInstant;
    }

    public void setFinishInstant(Instant finishInstant) {
        this.finishInstant = finishInstant;
    }
}
