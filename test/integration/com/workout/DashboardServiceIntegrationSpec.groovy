package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec

class DashboardServiceIntegrationSpec extends IntegrationSpec {
    SpringSecurityService springSecurityService

    DashboardService dashboardService

    User user
    User other

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

        other = new User(
                username: 'otherTestUser',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'otherTest@user-test.com',
                preferredDistanceUnits: 'mi'
        )
        other.save(flush: true)

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

    void "recentlyAccomplishedGoals"() {
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
                accomplished: true,
                dateAccomplished: (new Date()).clearTime().previous()
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

        Goal goal4 = new Goal(
                user: other,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next(),
                accomplished: true,
                dateAccomplished: (new Date()).clearTime()
        )
        goal4.save(flush: true)

        List<Goal> expected = [goal3, goal2]

        when:
        List<Goal> result = dashboardService.recentlyAccomplishedGoals()

        then:
        result == expected
    }

    void "recentlyCreatedActivities"() {
        given:
        List<Activity> expected = []

        Activity oldestActivity = new Activity(
                user: user,
                activityType: ActivityType.RUNNING,
                amount: 18,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Oldest Activity"
        )
        oldestActivity.save(flush: true)

        (1..25).each {
            Activity activity = new Activity(
                    user: user,
                    activityType: ActivityType.RUNNING,
                    amount: 18,
                    metric: MetricType.DISTANCE,
                    start: new Date().previous(),
                    duration: 45,
                    notes: "Activity $it"
            )
            activity.save(flush: true)

            expected.add(activity)
        }

        expected = expected.reverse()

        Activity otherActivity = new Activity(
                user: other,
                activityType: ActivityType.RUNNING,
                amount: 18,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Other Activity"
        )
        otherActivity.save(flush: true)

        when:
        List<Activity> result = dashboardService.recentlyCreatedActivities()

        then:
        result == expected
    }
}