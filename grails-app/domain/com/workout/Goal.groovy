package com.workout

class Goal {
    String title
    ActivityType activityType = ActivityType.RUNNING
    MetricType metric = MetricType.DISTANCE
    BigDecimal targetAmount = 0.0
    Date targetDate
    BigDecimal currentAmount = 0.0
    Boolean accomplished = false
    Date dateAccomplished
    Date dateCreated

    static belongsTo = [user: User]

    static constraints = {
        user()
        title blank: false, maxSize: 150
        activityType()
        metric()
        targetAmount min: 0.0
        targetDate validator: { targetDate ->
            Date today = (new Date()).clearTime()
            if(targetDate && targetDate < today) {
                return ['targetDate.mustBeInFuture']
            }
        }
        currentAmount min: 0.0
        dateAccomplished nullable: true
    }
}
