package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional
class GoalService {

    SpringSecurityService springSecurityService
    GoalActivityService goalActivityService

    def create(Goal goal) {
        goal.user = springSecurityService.currentUser
        goal.save()
    }

    def update(Goal goal) {
        goal.determineAccomplishedState()
        goal.save()
    }

    def delete(Goal goal) {
        goal.activities.each {
            goalActivityService.remove(goal, it)
        }
        goal.delete()
    }

    @Transactional(readOnly = true)
    boolean isAuthorized(Goal goal) {
        return goal.user.id == springSecurityService.loadCurrentUser().id
    }
}
