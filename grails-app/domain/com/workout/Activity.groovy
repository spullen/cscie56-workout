package com.workout

class Activity {
    ActivityType activityType = ActivityType.RUNNING
    BigDecimal amount
    MetricType metric = MetricType.MILES
    Date start
    Date end
    BigDecimal duration
    String notes

    static constraints = {
        activityType blank: false, inList: ActivityType.values() as List
        amount blank: false, min: 0.1
        metric blank: false, inList: MetricType.values() as List
        start blank: false
        end blank: false
        duration nullable: true
        notes nullable: true, maxSize: 2000
    }
}

public enum ActivityType {
    RUNNING('Running'),
    CYCLING('Cycling'),
    WALKING('Walking'),
    PUSH_UPS('Push Ups'),
    SIT_UPS('Sit Ups')

    String id

    ActivityType(String id) {
        this.id = id
    }
}

public enum MetricType {
    MILES('Miles'),
    REPS('Reps'),
    COUNT('Count')

    String id

    MetricType(String id) {
        this.id = id
    }
}
