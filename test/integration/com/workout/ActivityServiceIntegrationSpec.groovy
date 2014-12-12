package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec

class ActivityServiceIntegrationSpec extends IntegrationSpec {
    SpringSecurityService springSecurityService
    GoalActivityService goalActivityService

    ActivityService activityService

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

    void "create"() {
        when: 'there are no matching goals'
        Activity activity = new Activity(
                activityType: ActivityType.RUNNING,
                amount: 5.5,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Felt good, could go further next time."
        )

        activityService.create(activity)

        then:
        Activity.count() == 1
        activity.id != null
        activity.userId == user.id
    }
}