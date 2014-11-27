package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Goal)
class GoalConstraintsSpec extends Specification {

    Goal goal

    def setup() {
        goal = new Goal(
                user: [id: 1] as User,
                title: '5K Training',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 15,
                targetDate: (new Date()).clearTime().next()
        )

        mockForConstraintsTests(Goal)
    }

    void "user"() {
        when: 'user is null'
        goal.user = null

        then: 'validation should fail'
        !goal.validate()
        goal.errors['user'] == 'nullable'

        when: 'user is provided'
        goal.user = [id: 1] as User

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()
    }

    void "title"() {
        when: 'title is null'
        goal.title = null

        then: 'validation should fail'
        !goal.validate()
        goal.errors['title'] == 'nullable'

        when: 'title is blank'
        goal.title = ''

        then: 'validation should fail'
        !goal.validate()
        goal.errors['title'] == 'blank'

        when: 'title is defined'
        goal.title = '5K Training'

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()
    }

    void "activityType"() {
        when: 'activity type is null'
        goal.activityType = null

        then: 'it should fail validation'
        !goal.validate()
        goal.errors['activityType'] == 'nullable'

        when: 'activity type is a valid activity type'
        goal.activityType = ActivityType.RUNNING

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()
    }

    void "metric"() {
        when: 'metric is null'
        goal.metric = null

        then: 'validation should fail'
        !goal.validate()
        goal.errors['metric'] == 'nullable'

        when: 'metric is a valid type'
        goal.metric = MetricType.REPS

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()
    }

    void "targetAmount"() {
        when: 'targetAmount is null'
        goal.targetAmount = null

        then: 'validation should fail'
        !goal.validate()
        goal.errors['targetAmount'] == 'nullable'

        when: 'targetAmount is less than 1.0'
        goal.targetAmount = 0.9

        then: 'validation should fail'
        !goal.validate()
        goal.errors['targetAmount'] == 'min'

        when: 'targetAmount is less than 0.01'
        goal.targetAmount = -1.0

        then: 'validation should fail'
        !goal.validate()
        goal.errors['targetAmount'] == 'min'

        when: 'when targetAmount is valid'
        goal.targetAmount = 1.0

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()

        when: 'when targetAmount is valid'
        goal.targetAmount = 5.4

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()
    }

    void "targetDate"() {
        when: 'targetDate is null'
        goal.targetDate = null

        then: 'validation should fail'
        !goal.validate()
        goal.errors['targetDate'] == 'nullable'

        when: 'targetDate is in the past'
        goal.targetDate = (new Date()).clearTime().previous()

        then: 'validation should fail'
        !goal.validate()
        goal.errors['targetDate'] == 'targetDate.cannotBeInThePast'

        when: 'targetDate is today'
        goal.targetDate = (new Date()).clearTime()

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()

        when: 'targetDate is in the future'
        goal.targetDate = (new Date()).clearTime().next()

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()
    }

    void "currentAmount"() {
        when: 'targetAmount is null'
        goal.currentAmount = null

        then: 'validation should fail'
        !goal.validate()
        goal.errors['currentAmount'] == 'nullable'

        when: 'currentAmount is less than 0.0'
        goal.currentAmount = -0.9

        then: 'validation should fail'
        !goal.validate()
        goal.errors['currentAmount'] == 'min'

        when: 'when currentAmount is valid'
        goal.currentAmount = 0.0

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()

        when: 'when currentAmount is valid'
        goal.currentAmount = 5.4

        then: 'validation should pass'
        goal.validate()
        !goal.hasErrors()
    }
}