package com.workout

import groovy.time.TimeCategory

import java.sql.Time

class Activity {
    ActivityType activityType = ActivityType.RUNNING
    BigDecimal amount
    MetricType metric = MetricType.DISTANCE
    Date start
    Date end
    String notes
    Date dateCreated

    static belongsTo = [user: User]

    static constraints = {
        user()
        activityType()
        amount blank: false, min: 0.01
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
        notes nullable: true, blank: true, maxSize: 2000
    }

    String getDuration() {
        if(start && end) {
            TimeCategory.minus(end, start)
        }
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
