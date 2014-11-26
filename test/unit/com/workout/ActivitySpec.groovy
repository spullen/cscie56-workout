package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Activity)
class ActivitySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "getDuration"() {
        when:
        Activity a = new Activity(
                user: [id: 1] as User,
                activityType: ActivityType.RUNNING,
                amount: 5.5,
                metric: MetricType.DISTANCE,
                start: new Date("12/10/2014 12:30:00"),
                end: new Date("12/10/2014 12:45:00"),
                notes: "Felt good, could go further next time."
        )

        then:
        a.duration == "15 minutes"
    }
}
