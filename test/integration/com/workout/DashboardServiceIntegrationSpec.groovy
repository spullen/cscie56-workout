package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec

class DashboardServiceIntegrationSpec extends IntegrationSpec {
    SpringSecurityService springSecurityService

    DashboardService dashboardService

    User user

    void setup() {
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

    void "activeGoals"() {
        given:
        Goal goal1 = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next().next(),
                accomplished: false,
                dateAccomplished: null
        )
        goal1.save(flush: true)

        Goal goal2 = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next(),
                accomplished: false,
                dateAccomplished: null
        )
        goal2.save(flush: true)

        Goal goal3 = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next(),
                accomplished: true,
                dateAccomplished: (new Date()).clearTime()
        )
        goal3.save(flush: true)

        User other = new User(
                username: 'otherTestUser',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'otherTest@user-test.com',
                preferredDistanceUnits: 'mi'
        )
        other.save(flush: true)

        Goal goal4 = new Goal(
                user: other,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next(),
                accomplished: false,
                dateAccomplished: null
        )
        goal4.save(flush: true)

        List<Goal> expected = [goal2, goal1]

        when:
        List<Goal> result = dashboardService.activeGoals()

        then:
        result == expected
    }
}