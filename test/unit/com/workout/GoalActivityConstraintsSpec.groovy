package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(GoalActivity)
class GoalActivityConstraintsSpec extends Specification {

    Activity activity = [id: 2] as Activity

    GoalActivity goalActivity

    def setup() {
        goalActivity = new GoalActivity(
                goal: [id: 1] as Goal,
                activity: [id: 1] as Activity
        )

        mockForConstraintsTests(GoalActivity)
    }

    void "goal"() {
        when: 'goal is null'
        goalActivity.goal = null

        then: 'validation should fail'
        !goalActivity.validate()
        goalActivity.errors['goal'] == 'nullable'

        when: 'goal is not null'
        goalActivity.goal = [id: 1] as Goal

        then: 'validation should pass'
        goalActivity.validate()
        !goalActivity.hasErrors()
    }

    void "activity"() {
        when: 'activity is null'
        goalActivity.activity = null

        then: 'validation should fail'
        !goalActivity.validate()
        goalActivity.errors['activity'] == 'nullable'

        /* TODO: figure out why this isn't working
        when: 'the goal activity already has the activity'
        GoalActivity existingGoalActivity = new GoalActivity(
                goal: [id: 1] as Goal,
                activity: activity
        )

        mockForConstraintsTests(GoalActivity, [existingGoalActivity])

        goalActivity.activity = activity

        then: 'validation should fail'
        !goalActivity.validate()
        goalActivity.errors['activity'] == 'unique'
        */

        when: 'goal activity has a unique activity for the goal'
        goalActivity.activity = [id: 1] as Activity

        then: 'validation should pass'
        goalActivity.validate()
        !goalActivity.hasErrors()
    }
}
