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
        // TODO: check if goal is complete or not
        goal.save(flush: true)
    }

    def delete(Goal goal) {
        goal.activities.each {
            goalActivityService.remove(goal, it)
        }
        goal.delete(flush: true)
    }
}
