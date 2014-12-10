package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec

@TestFor(GoalService)
@Mock(SpringSecurityService)
class GoalServiceIntegrationSpec extends IntegrationSpec {

    /*
    def springSecurityServiceMock
    User currentUser*/

    def setup() {
        /*
        currentUser = new User(
                username: 'me',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'testUser@test.com',
                preferredDistanceUnits: 'mi'
        )
        currentUser.save()
        */

        /*
        springSecurityServiceMock = mockFor(SpringSecurityService)
        springSecurityServiceMock.demand.currentUser() { -> currentUser }
        springSecurityServiceMock.demand.loadCurrentUser() { -> currentUser }

        service.springSecurityService = springSecurityServiceMock.createMock()

        service.springSecurityService = [
                getCurrentUser: { -> currentUser },
                loadCurrentUser: { -> currentUser },
                currentUser: currentUser
        ] as SpringSecurityService
        */
    }

    void "create goal"() {
        given:
        User currentUser = new User(
                username: 'me',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'testUser@test.com',
                preferredDistanceUnits: 'mi'
        )
        currentUser.save(flush: true)

        def springSecurityServiceMock = mockFor(SpringSecurityService, true)
        springSecurityServiceMock.demand.getCurrentUser() { -> currentUser }
        springSecurityServiceMock.demand.loadCurrentUser() { -> currentUser }

        service.springSecurityService = springSecurityServiceMock.createMock()


        def goal = new Goal(
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )

        when:
        service.create(goal)

        then:
        goal.id
        goal.userId == currentUser.id
    }

    /*
    void "update goal"() {

    }

    void "isAuthorized for same user"() {
        given:
        def goal = new Goal(
                user: currentUser,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )
        goal.save()

        when:
        boolean isAuthorized = service.isAuthorized(goal)

        then:
        isAuthorized
    }

    void "isAuthorized for different user"() {
        given:
        def otherUser = new User(
                username: 'me',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'testUser@test.com',
                preferredDistanceUnits: 'mi'
        )
        otherUser.save()

        def otherGoal = new Goal(
                user: otherUser,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )
        otherGoal.save()

        when:
        boolean isAuthorized = service.isAuthorized(otherGoal)

        then:
        !isAuthorized
    }
    */
}
