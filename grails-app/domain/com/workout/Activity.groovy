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
        activityType()
        amount min: 0.0
        metric()
        start()
        end validator: { endDate, activity ->
            if(endDate && activity.start && endDate < activity.start) {
                return ['endDateTimeMustComeAfterStartDateTime']
            }
        }
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
