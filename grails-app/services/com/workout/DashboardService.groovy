package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DashboardService {

    SpringSecurityService springSecurityService

    

}