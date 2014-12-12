package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec

class GoalServiceIntegrationSpec extends IntegrationSpec {
    SpringSecurityService springSecurityService
    GoalService goalService

    User user

    def setup() {
        user = new User(
                username: 'testUser',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'test@user-test.com',
                preferredDistanceUnits: 'mi'
        )
        user.save(flush: true)

        springSecurityService.reauthenticate('testUser', 'password')
    }

    void "create goal"() {
        given:
        Goal goal = new Goal(
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )

        when:
        goalService.create(goal)

        then:
        goal.id != null
        goal.userId == user.id
    }
}