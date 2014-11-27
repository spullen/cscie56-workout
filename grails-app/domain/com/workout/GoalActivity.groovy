package com.workout

class GoalActivity {
    Goal goal
    Activity activity

    static constraints = {
        activity unique: 'goal'
    }
}
