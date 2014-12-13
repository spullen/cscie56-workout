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

        when: 'there is a matching goal'
        Goal goal = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )
        goal.save(flush: true)

        Activity activity1 = new Activity(
                activityType: ActivityType.RUNNING,
                amount: 18,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Felt good, could go further next time."
        )

        activityService.create(activity1)

        then:
        Activity.count() == 2
        activity1.id != null
        goal.currentAmount == 18
        GoalActivity.count() == 1

        when: 'there is a matching goal and the activity amount is more than the target'
        Goal goal1 = new Goal(
                user: user,
                title: 'Test Goal',
                activityType: ActivityType.RUNNING,
                metric: MetricType.DISTANCE,
                targetAmount: 50.0,
                targetDate: (new Date()).clearTime().next()
        )
        goal1.save(flush: true)

        Activity activity2 = new Activity(
                activityType: ActivityType.RUNNING,
                amount: 51,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Felt good, could go further next time."
        )

        activityService.create(activity2)

        then: 'the goal should be set as accomplished'
        goal1.accomplished
        goal1.dateAccomplished != null
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

        Long goalId = goal.id

        Activity activity = new Activity(
                activityType: ActivityType.RUNNING,
                amount: 5.5,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Felt good, could go further next time."
        )

        activityService.create(activity)

        Long activityId = activity.id

        when: 'updating the activity that goes past the target amount of goal'
        activity.amount = 51
        activityService.update(activity)

        Activity.withSession { session ->
            session.flush()
            session.clear()
        }

        Goal.withSession { session ->
            session.flush()
            session.clear()
        }

        activity = Activity.get(activityId)
        goal = Goal.get(goalId)

        then:
        activity.amount == 51
        goal.currentAmount == 51
        goal.accomplished
        goal.dateAccomplished != null

        when: 'updating the activity that goes below the target amount of goal'
        activity.amount = 40
        activityService.update(activity)

        Activity.withSession { session ->
            session.flush()
            session.clear()
        }

        Goal.withSession { session ->
            session.flush()
            session.clear()
        }

        activity = Activity.get(activityId)
        goal = Goal.get(goalId)

        then:
        activity.amount == 40
        goal.currentAmount == 40
        !goal.accomplished
        goal.dateAccomplished == null
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

        Activity activity = new Activity(
                user: user,
                activityType: ActivityType.RUNNING,
                amount: 51,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Felt good, could go further next time."
        )

        when:
        boolean result = activityService.isAuthorized(activity)

        then:
        result

        when:
        springSecurityService.reauthenticate('otherUser', 'password')
        result = activityService.isAuthorized(activity)

        then:
        !result
    }
}