package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional
class GoalService {

    SpringSecurityService springSecurityService

    def create(Goal goal) {
        goal.user = springSecurityService.currentUser
        goal.save()
    }

    def update(Goal goal) {
        goal.save(flush: true)
    }

    def delete(Goal goal) {
        goal.delete(flush: true)
    }
}
