package com.workout

class Goal {
    User user
    String title
    ActivityType activityType = ActivityType.RUNNING
    MetricType metric = MetricType.DISTANCE
    BigDecimal targetAmount = 1.0
    Date targetDate
    BigDecimal currentAmount = 0.0
    Boolean accomplished = false
    Date dateAccomplished
    Date dateCreated

    static belongsTo = Activity
    static hasMany = [goalActivities: GoalActivity]

    static mapping = {
        activityType index: 'activityTypeIndex'
        metric index: 'metricTypeIndex'
    }

    static constraints = {
        user()
        title blank: false, maxSize: 150
        activityType()
        metric()
        targetAmount min: 1.0
        targetDate validator: { targetDate ->
            Date today = (new Date()).clearTime()
            if(targetDate && targetDate < today) {
                return ['targetDate.cannotBeInThePast']
            }
        }
        currentAmount min: 0.0
        dateAccomplished nullable: true
    }

    List<Activity> getActivities() {
        goalActivities.collect { it.activity }
    }

    void determineAccomplishedState() {
        if(accomplished) {
            if(currentAmount < targetAmount) {
                accomplished = false
                dateAccomplished = null
            }
        } else {
            if(currentAmount >= targetAmount) {
                accomplished = true
                dateAccomplished = new Date()
            }
        }
    }
}
