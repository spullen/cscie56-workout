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
}