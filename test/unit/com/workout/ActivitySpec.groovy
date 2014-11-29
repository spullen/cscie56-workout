package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Activity)
class ActivitySpec extends Specification {

    void "canUpdate"() {
        given:
        def activity = new Activity(
                user: [id: 1] as User,
                activityType: ActivityType.RUNNING,
                amount: 5.5,
                metric: MetricType.DISTANCE,
                start: new Date().previous(),
                duration: 45,
                notes: "Felt good, could go further next time."
        )

        when: 'the activity was created less than 24 hours ago'
        activity.dateCreated = new Date()

        then: 'can update should be true'
        activity.canUpdate()

        when: 'the activity was created 24 hours ago'
        activity.dateCreated = new Date() - 1

        then: 'can update should be false'
        !activity.canUpdate()

        when: 'the activity was created more than 24 hours ago'
        activity.dateCreated = new Date() - 2

        then: 'can update should be false'
        !activity.canUpdate()
    }
}
