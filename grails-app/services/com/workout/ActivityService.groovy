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
        activity.save(flush: true)

        // TODO: goal integration
        // This could be many-to-many so need to find all goals and update
        // Find active goal for user, activity type, and metric
        // Re-calculate current amount and update
        // Decide if goal has been completed (this could be mean going from completed to incomplete if adjustments go under target)
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
