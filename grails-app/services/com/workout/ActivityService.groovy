package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional
class ActivityService {

    SpringSecurityService springSecurityService
    GoalActivityService goalActivityService

    def create(Activity activity) {
        User user = springSecurityService.currentUser

        activity.user = user
        if(activity.save(flush: true)) {
            List<Goal> goals = Goal.withCriteria {
                eq('user', user)
                eq('activityType', activity.activityType)
                eq('metric', activity.metric)
                eq('accomplished', false)
            }

            goals.each {
                it.currentAmount += activity.amount

                if(it.currentAmount >= it.targetAmount) {
                    it.accomplished = true
                    it.dateAccomplished = new Date()
                }

                it.save(flush: true)

                goalActivityService.add(it, activity)
            }
        }
    }

    def update(Activity activity) {
        BigDecimal oldAmount = activity.getPersistentValue('amount')

        activity.goals.each {
            it.currentAmount -= oldAmount
            it.currentAmount += activity.amount

            // TODO: move this to a method on goal
            if(it.accomplished && it.currentAmount < it.targetAmount) {
                it.accomplished = false
                it.dateAccomplished = null
            }

            it.save(flush: true)
        }

        activity.save(flush: true)
    }

    def delete(Activity activity) {
        activity.goals.each {
            it.currentAmount -= activity.amount

            if(it.accomplished && it.currentAmount < it.targetAmount) {
                it.accomplished = false
                it.dateAccomplished = null
            }

            it.save(flush: true)

            goalActivityService.remove(it, activity)
        }

        activity.delete(flush: true)
    }
}
