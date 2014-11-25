package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Activity)
class ActivityConstraintsSpec extends Specification {

    Activity activity

    def setup() {
        activity = new Activity(
                activityType: ActivityType.RUNNING,
                amount: 5.5,
                metric: MetricType.MILES,
                start: new Date("12/10/2014 12:30:00"),
                end: new Date("12/10/2014 12:45:00"),
                duration: 15,
                notes: "Felt good, could go further next time."
        )

        mockForConstraintsTests(Activity)
    }

    void "activityType"() {
        when: 'activity type is null'
        activity.activityType = null

        then: 'it should fail validation'
        !activity.validate()
        activity.errors['activityType'] == 'nullable'

        when: 'activity type is not a valid activity type'
        activity.activityType = 'Garbage'

        then: 'it should fail validation'
        !activity.validate()
        activity.errors['activityType'] == 'inList'

        when: 'activity type is a valid activity type'
        activity.activityType = ActivityType.RUNNING

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }

    void "amount"() {
        when: 'amount is null'
        activity.amount = null

        then: 'validation should fail'
        !activity.validate()
        activity.errors['amount'] == 'nullable'

        when: 'amount is less than 0.0'
        activity.amount = -1.0

        then: 'validation should fail'
        !activity.validate()
        activity.errors['amount'] == 'min'

        when: 'when amount is valid'
        activity.amount = 0.01

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()

        when: 'when amount is valid'
        activity.amount = 5.4

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }

    void "metric"() {
        when: 'metric is null'
        activity.metric = null

        then: 'validation should fail'
        !activity.validate()
        activity.errors['metric'] == 'nullable'

        when: 'metric is not a valid type'
        activity.metric = 'Garbage'

        then: 'validation should fail'
        !activity.validate()
        activity.errors['metric'] == 'inList'

        when: 'metric is a valid type'
        activity.metric = MetricType.REPS

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }
}