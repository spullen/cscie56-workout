package com.workout

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class DashboardController {

    DashboardService dashboardService

    def index() {
        return [
                activeGoals: dashboardService.activeGoals(),
                accomplishedGoals: dashboardService.recentlyAccomplishedGoals(),
                activities: dashboardService.recentlyCreatedActivities()
        ]
    }
}
