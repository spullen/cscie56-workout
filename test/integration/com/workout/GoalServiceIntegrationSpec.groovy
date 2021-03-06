package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec

class GoalServiceIntegrationSpec extends IntegrationSpec {
    SpringSecurityService springSecurityService
    GoalActivityService goalActivityService

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

    void "create"() {
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
        Goal.count() == 1
        goal.id != null
        goal.userId == user.id
    }

    void "update"() {
        given:
        Goal goal = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )
        goal.save(flush: true)

        def id = goal.id

        goal.currentAmount = 10

        when:
        goalService.update(goal)

        Goal.withSession { session ->
            session.flush()
            session.clear()
        }

        goal = Goal.get(id)

        then:
        goal.currentAmount == 10

        when: "currentAmount surpasses the targetAmount"
        goal.currentAmount = 51
        goalService.update(goal)

        Goal.withSession { session ->
            session.flush()
            session.clear()
        }

        goal = Goal.get(id)

        then:
        goal.currentAmount == 51
        goal.accomplished
        goal.dateAccomplished != null

        when: "currentAmount falls below the targetAmount"
        goal.targetAmount = 52
        goalService.update(goal)

        Goal.withSession { session ->
            session.flush()
            session.clear()
        }

        goal = Goal.get(id)

        then:
        goal.targetAmount == 52
        !goal.accomplished
        goal.dateAccomplished == null
    }

    void "delete"() {
        given:
        Goal goal1 = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )
        goal1.save(flush: true)

        Long goal1Id = goal1.id

        Goal goal2 = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next().next()
        )
        goal2.save(flush: true)

        Long goal2Id = goal2.id

        Activity a = new Activity(
                user: user,
                activityType: ActivityType.RUNNING,
                amount: 5.5,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Felt good, could go further next time."
        )
        a.save(flush: true)

        goalActivityService.add(goal2, a)

        when:
        goalService.delete(goal1)

        Goal.withSession { session ->
            session.flush()
            session.clear()
        }

        Goal result = Goal.get(goal1Id)

        then:
        result == null

        when: 'delete a goal with GoalActivity entries'
        goalService.delete(goal2)

        result = Goal.get(goal2Id)

        then:
        result == null
        GoalActivity.count() == 0
    }

    void "isAuthorized"() {
        given:
        new User(
                username: 'otherUser',
                password: 'password',
                firstName: 'Other',
                lastName: 'User',
                email: 'otherUser@user-test.com',
                preferredDistanceUnits: 'mi'
        ).save(flush: true)

        Goal goal = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )
        goal.save(flush: true)

        when:
        boolean isAuthorized = goalService.isAuthorized(goal)

        then:
        isAuthorized

        when:
        springSecurityService.reauthenticate('otherUser', 'password')
        isAuthorized = goalService.isAuthorized(goal)

        then:
        !isAuthorized
    }
}