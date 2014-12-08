package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional
class ActivityService {

    SpringSecurityService springSecurityService
    GoalActivityService goalActivityService

    def create(Activity activity) {
        User user = springSecurityService.loadCurrentUser()

        activity.user = user
        if(activity.save()) {
            List<Goal> goals = Goal.withCriteria {
                eq('user', user)
                eq('activityType', activity.activityType)
                eq('metric', activity.metric)
                eq('accomplished', false)
            }

            goals.each {
                it.currentAmount += activity.amount
                it.determineAccomplishedState()
                it.save()

                goalActivityService.add(it, activity)
            }
        }
    }

    def update(Activity activity) {
        if(activity.isDirty('amount')) {
            BigDecimal oldAmount = activity.getPersistentValue('amount')

            activity.goals.each {
                it.currentAmount -= oldAmount
                it.currentAmount += activity.amount
                it.determineAccomplishedState()
                it.save()
            }
        }

        activity.save()
    }

    def delete(Activity activity) {
        activity.goals.each {
            it.currentAmount -= activity.amount
            it.determineAccomplishedState()
            it.save()

            goalActivityService.remove(it, activity)
        }

        activity.delete()
    }

    @Transactional(readOnly = true)
    boolean isAuthorized(Activity activity) {
        return activity.user.id == springSecurityService.loadCurrentUser().id
    }
}
