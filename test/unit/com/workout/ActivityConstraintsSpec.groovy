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

        when: 'amount is less than 0.01'
        activity.amount = 0.001

        then: 'validation should fail'
        !activity.validate()
        activity.errors['amount'] == 'min'

        when: 'amount is less than 0.01'
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

        when: 'metric is a valid type'
        activity.metric = MetricType.REPS

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }

    void "start"() {
        when: 'start is null'
        activity.start = null

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()

        when: 'start is not null'
        activity.start = new Date('11/10/2014 11:20:00')
        activity.end = new Date('11/10/2014 11:30:00')

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }

    void "end"() {
        when: 'end is before the start date'
        activity.start = new Date('11/10/2014 11:30:00')
        activity.end = new Date('11/10/2014 11:20:00')

        then: 'validation should fail'
        !activity.validate()
        activity.errors['end'] != null

        when: 'start is not null and end is null'
        activity.start = new Date('11/10/2014 11:20:00')
        activity.end = null

        then: 'validation should fail'
        !activity.validate()
        activity.errors['end'] == 'end.required'

        when: 'start and end are null'
        activity.start = null
        activity.end = null

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()

        when: 'end is after the start'
        activity.start = new Date('11/10/2014 11:20:00')
        activity.end = new Date('11/10/2014 11:30:00')

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }

    void "duration"() {
        when: 'duration is less than 0.01'
        activity.start = new Date('10/22/2014 09:30:00')
        activity.end = new Date('10/22/2014 10:00:00')
        activity.duration = 0.001

        then: 'validation should fail'
        !activity.validate()
        activity.errors['duration'] == 'min'

        when: 'duration is less than 0.01'
        activity.start = new Date('10/22/2014 09:30:00')
        activity.end = new Date('10/22/2014 10:00:00')
        activity.duration = -1.0

        then: 'validation should fail'
        !activity.validate()
        activity.errors['duration'] == 'min'

        when: 'start and end are not null and duration is null'
        activity.start = new Date('10/22/2014 09:30:00')
        activity.end = new Date('10/22/2014 10:00:00')
        activity.duration = null

        then: 'validation should fail'
        !activity.validate()
        activity.errors['duration'] == 'duration.required'

        when: 'start, end, and duration are null'
        activity.start = null
        activity.end = null
        activity.duration = null

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()

        when: 'duration is valid'
        activity.start = new Date('10/22/2014 09:30:00')
        activity.end = new Date('10/22/2014 10:00:00')
        activity.duration = 0.01

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()

        when: 'duration is valid'
        activity.start = new Date('10/22/2014 09:30:00')
        activity.end = new Date('10/22/2014 10:00:00')
        activity.duration = 5.4

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }
}