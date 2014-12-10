package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserService {

    SpringSecurityService springSecurityService

    def getCurrentUser() {
        springSecurityService.currentUser
    }

    def loadCurrentUser() {
        springSecurityService.loadCurrentUser()
    }
}