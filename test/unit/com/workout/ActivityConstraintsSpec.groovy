package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification
import java.nio.CharBuffer

@TestFor(Activity)
class ActivityConstraintsSpec extends Specification {

    Activity activity

    def setup() {
        activity = new Activity(
                user: [id: 1] as User,
                activityType: ActivityType.RUNNING,
                amount: 5.5,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Felt good, could go further next time."
        )

        mockForConstraintsTests(Activity)
    }

    void "user"() {
        when: 'user is null'
        activity.user = null

        then: 'validation should fail'
        !activity.validate()
        activity.errors['user'] == 'nullable'

        when: 'user is not null'
        activity.user = [id: 1] as User

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
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

        then: 'validation should fail'
        !activity.validate()
        activity.errors['start'] == 'nullable'

        when: 'start is in the future'
        activity.start = (new Date()).next()

        then: 'validation should fail'
        !activity.validate()
        activity.errors['start'] != null

        when: 'start is not null'
        activity.start = new Date('11/10/2014 11:20:00')

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }

    void "duration"() {
        when: 'duration is null'
        activity.duration = null

        then: 'validation should fail'
        !activity.validate()
        activity.errors['duration'] == 'nullable'

        when: 'duration is less than 0.01'
        activity.duration = 0.001

        then: 'validation should fail'
        !activity.validate()
        activity.errors['duration'] == 'min'

        when: 'duration is less than 0.01'
        activity.duration = -1.0

        then: 'validation should fail'
        !activity.validate()
        activity.errors['duration'] == 'min'

        when: 'duration is valid'
        activity.duration = 0.01

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()

        when: 'duration is valid'
        activity.duration = 5.4

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }

    void "notes"() {
        when: 'notes is null'
        activity.notes = null

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()

        when: 'notes is blank'
        activity.notes = ''

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()

        when: 'notes is more than 2000 characters'
        activity.notes = "_${CharBuffer.allocate(1999).toString()}_"

        then: 'validation should fail'
        !activity.validate()
        activity.errors['notes'] == 'maxSize'

        when: 'notes is between 0 and 2000 character'
        activity.notes = 'Good run today!'

        then: 'validation should pass'
        activity.validate()
        !activity.hasErrors()
    }
}