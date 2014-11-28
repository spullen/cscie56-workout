package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Goal)
class GoalSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "determineAccomplishedState"() {
        given:
        Goal goal = new Goal(
                user: [id: 1] as User,
                title: '5K Training',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 15,
                targetDate: (new Date()).clearTime().next()
        )

        when: 'currentAmount is less than the targetAmount'
        goal.currentAmount = 10
        goal.determineAccomplishedState()

        then: 'accomplished should be false and dateAccomplished should be null'
        !goal.accomplished
        goal.dateAccomplished == null

        when: 'accomplished is true and dateAccomplished is set and currentAmount is less than the target'
        goal.accomplished = true
        goal.dateAccomplished = new Date()
        goal.currentAmount = 10
        goal.determineAccomplishedState()

        then: 'accomplished should be false and dateAccomplished should be null'
        !goal.accomplished
        goal.dateAccomplished == null

        when: 'accomplished is false and dateAccomplished is null and currentAmount is over the target amount'
        goal.accomplished = false
        goal.dateAccomplished = null
        goal.currentAmount = 16
        goal.determineAccomplishedState()

        then: 'accomplished should be true and dateAccomplished should be set'
        goal.accomplished
        goal.dateAccomplished != null
    }
}
