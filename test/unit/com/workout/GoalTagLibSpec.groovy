package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(GoalTagLib)
class GoalTagLibSpec extends Specification {

    void "percentage"() {
        given:
        Goal goal = new Goal(
                user: [id: 1] as User,
                title: '5K Training',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                currentAmount: 20,
                targetAmount: 100,
                targetDate: (new Date()).clearTime().next()
        )

        expect:
        tagLib.percentage().toString() == ''
        tagLib.percentage(goal: goal).toString() == '20%'
    }
}
