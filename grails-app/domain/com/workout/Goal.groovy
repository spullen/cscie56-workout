package com.workout

class Goal {
    String title
    ActivityType activityType = ActivityType.RUNNING
    BigDecimal targetAmount = 0
    MetricType metric
    Date targetDate
    BigDecimal currentAmount = 0
    Boolean accomplished = false

    static belongsTo = [user: User]

    static constraints = {
    }
}
