package com.workout

class GoalTagLib {
    static namespace = "goal"

    static defaultEncodeAs = [taglib:'html']

    def percentage = { attrs ->
        Goal goal = attrs.goal

        if(goal) {
            Integer p = (goal.currentAmount / goal.targetAmount) * 100
            out << "${p}%"
        }

        out
    }
}
