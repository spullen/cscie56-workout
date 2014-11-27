package com.workout

class Activity {
    ActivityType activityType = ActivityType.RUNNING
    MetricType metric = MetricType.DISTANCE
    BigDecimal amount
    Date start
    BigDecimal duration
    String notes
    Date dateCreated

    static belongsTo = [user: User]

    static constraints = {
        user()
        activityType()
        metric()
        amount blank: false, min: 0.01
        start()
        duration min: 0.01
        notes nullable: true, blank: true, maxSize: 2000
    }
}

public enum ActivityType {
    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    PUSH_UPS("Push Ups"),
    SIT_UPS("Sit Ups"),
    PULL_UPS("Pull Ups"),
    WEIGHT_LIFTING("Weight Lifting")

    String id

    ActivityType(String id) {
        this.id = id
    }

    String toString() {
        id
    }
}

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
