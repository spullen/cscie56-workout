package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DashboardService {

    SpringSecurityService springSecurityService

    List<Goal> activeGoals() {
        User user = springSecurityService.loadCurrentUser()
        Goal.findAllByUserAndAccomplished(user, false, [sort: 'targetDate', order: 'asc'])
    }

    List<Goal> recentlyAccomplishedGoals() {
        User user = springSecurityService.loadCurrentUser()
        Date start = new Date().clearTime() - 7
        Date end = new Date().clearTime() + 1
        Goal.findAllByUserAndDateAccomplishedBetween(user, start, end, [sort: 'dateAccomplished', order: 'desc'])
    }

    List<Activity> recentlyCreatedActivities() {
        User user = springSecurityService.loadCurrentUser()
        Activity.findAllByUser(user, [sort: 'dateCreated', order: 'desc', max: 25])
    }
}