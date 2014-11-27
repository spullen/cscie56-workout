package com.workout

import grails.transaction.Transactional

@Transactional
class GoalActivityService {

    GoalActivity add(Goal goal, Activity activity) {
        GoalActivity ga = GoalActivity.findByGoalAndActivity(goal, activity)

        if(!ga) {
            ga = new GoalActivity()
            goal.addToGoalActivities(ga)
            activity.addToGoalActivities(ga)
            ga.save()
        }

        ga
    }

    void remove(Goal goal, Activity activity) {
        GoalActivity ga = GoalActivity.findByGoalAndActivity(goal, activity)

        if(ga) {
            goal.removeFromGoalActivities(ga)
            activity.removeFromGoalActivities(ga)
            ga.delete()
        }
    }
}
