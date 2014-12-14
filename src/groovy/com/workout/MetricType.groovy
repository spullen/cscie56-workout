package com.workout

public enum MetricType {
    DISTANCE('Distance'),
    REPS('Reps'),
    COUNT('Count')

    String id

    MetricType(String id) {
        this.id = id
    }

    String toString() {
        id
    }
}