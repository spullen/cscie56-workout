package com.workout

class Activity {
    ActivityType activityType = ActivityType.RUNNING
    BigDecimal amount
    MetricType metric = MetricType.MILES
    Date start
    Date end
    BigDecimal duration
    String notes

    static belongsTo = [user: User]

    static constraints = {
        user()
        activityType()
        amount min: 0.01
        metric()
        start nullable: true
        end nullable: true, validator: { endDate, activity ->
            if(activity.start && !endDate) {
                return ['end.required']
            }

            if(endDate && activity.start && endDate < activity.start) {
                return ['end.mustComeAfterStart']
            }
        }
        duration nullable: true, min: 0.01, validator: { duration, activity ->
            if(activity.start && activity.end && !duration) {
                return ['duration.required']
            }
        }
        notes nullable: true, blank: true, maxSize: 2000
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
