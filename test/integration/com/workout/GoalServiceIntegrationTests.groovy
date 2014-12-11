package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.TestFor
import org.junit.Before
import org.junit.Test

@TestFor(GoalService)
class GoalServiceIntegrationTests {

    User currentUser

    @Before
    void setUp() {
        currentUser = new User(
                username: 'me',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'testUser@test.com',
                preferredDistanceUnits: 'mi'
        )
        currentUser.save(flush: true)

        service.springSecurityService = [
                getCurrentUser: { -> currentUser },
                loadCurrentUser: { -> currentUser }
        ] as SpringSecurityService
    }

    @Test
    void testCreateGoal() {
        Goal goal = new Goal(
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )

        service.create(goal)

        assert goal.id != null
        assert goal.userId == currentUser.id
    }
}