package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional
class ActivityService {

    SpringSecurityService springSecurityService

    def create(Activity activityInstance) {
        activityInstance.user = springSecurityService.currentUser
        activityInstance.save()

        // TODO: goal integration
    }
}
