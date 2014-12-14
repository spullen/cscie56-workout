package com.workout

class Activity {
    ActivityType activityType = ActivityType.RUNNING
    MetricType metric = MetricType.DISTANCE
    BigDecimal amount
    Date start
    BigDecimal duration
    String notes
    Date dateCreated

    static belongsTo = [user: User]
    static hasMany = [goals: Goal, goalActivities: GoalActivity]

    static def startValidator = { start ->
        Date now = new Date()
        if(start && start.after(now)) {
            return ['start.cannotBeInFuture']
        }
    }

    static constraints = {
        user()
        activityType()
        metric()
        amount blank: false, min: 0.01
        start validator: startValidator
        duration min: 0.01
        notes nullable: true, blank: true, maxSize: 2000
    }

    List<Goal> getGoals() {
        goalActivities.collect { it.goal }
    }

    boolean canUpdate() {
        Date today = new Date();
        ((today.time - dateCreated.time) / 1000 / 60 / 60 ) < 24
    }
}