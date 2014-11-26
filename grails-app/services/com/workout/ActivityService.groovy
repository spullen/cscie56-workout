package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional
class ActivityService {

    SpringSecurityService springSecurityService

    def create(Activity activity) {
        activity.user = springSecurityService.currentUser
        activity.save()

        // TODO: goal integration
        // This could be many-to-many so need to find all goals and update
        // Find active goal for user, activity type, and metric
        // Update current amount value
        // Decide if goal has been completed
    }

    def update(Activity activity) {
        activity.save()

        // TODO: goal integration
        // This could be many-to-many so need to find all goals and update
        // Find active goal for user, activity type, and metric
        // Re-calculate current amount and update
        // Decide if goal has been completed (this could be mean going from completed to incomplete if adjustments go under target)
    }
}
