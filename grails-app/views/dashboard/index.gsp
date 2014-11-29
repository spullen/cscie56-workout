<%@ page import="com.workout.Activity" %>
<%@ page import="com.workout.Goal" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Workout Dashboard</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Dashboard</h1>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <p>
                <g:link controller="activity" action="create">Add Activity</g:link>
            </p>
            <p>
                <g:link controller="goal" action="create">Create A New Goal</g:link>
            </p>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2>Active Goals</h2>

            <g:if test="${activeGoals}">
                <table class="table table-responsive table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>${message(code: 'goal.title.label', default: 'Title')}</th>
                            <th>${message(code: 'goal.activityType.label', default: 'Activity Type')}</th>
                            <th>${message(code: 'goal.metric.label', default: 'Metric')}</th>
                            <th>${message(code: 'goal.targetAmount.label', default: 'Target Amount')}</th>
                            <th>${message(code: 'goal.targetDate.label', default: 'Target Date')}</th>
                            <th>${message(code: 'goal.currentAmount.label', default: 'Current Amount')}</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${activeGoals}" status="i" var="activeGoal">
                        <tr>
                            <td><g:link controller="goal" action="show" id="${activeGoal.id}">${fieldValue(bean: activeGoal, field: "title")}</g:link></td>
                            <td>${fieldValue(bean: activeGoal, field: "activityType")}</td>
                            <td>${fieldValue(bean: activeGoal, field: "metric")}</td>
                            <td>${fieldValue(bean: activeGoal, field: "targetAmount")}</td>
                            <td><g:formatDate date="${activeGoal.targetDate}" format="MM/dd/yyyy" /></td>
                            <td>${fieldValue(bean: activeGoal, field: "currentAmount")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </g:if>
            <g:else>
                <p>
                    You currently have no goals set. <g:link controller="goal" action="create">Create some</g:link>
                </p>
            </g:else>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2>Recently Accomplished Goals</h2>

            <g:if test="${accomplishedGoals}">
                <table class="table table-responsive table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>${message(code: 'goal.title.label', default: 'Title')}</th>
                        <th>${message(code: 'goal.activityType.label', default: 'Activity Type')}</th>
                        <th>${message(code: 'goal.metric.label', default: 'Metric')}</th>
                        <th>${message(code: 'goal.targetAmount.label', default: 'Target Amount')}</th>
                        <th>${message(code: 'goal.targetDate.label', default: 'Target Date')}</th>
                        <th>${message(code: 'goal.dateAccomplished.label', default: 'Date Accomplished')}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accomplishedGoals}" status="i" var="accomplishedGoal">
                        <tr>
                            <td><g:link controller="goal" action="show" id="${accomplishedGoal.id}">${fieldValue(bean: accomplishedGoal, field: "title")}</g:link></td>
                            <td>${fieldValue(bean: accomplishedGoal, field: "activityType")}</td>
                            <td>${fieldValue(bean: accomplishedGoal, field: "metric")}</td>
                            <td>${fieldValue(bean: accomplishedGoal, field: "targetAmount")}</td>
                            <td><g:formatDate date="${accomplishedGoal.targetDate}" format="MM/dd/yyyy" /></td>
                            <td><g:formatDate date="${accomplishedGoal.dateAccomplished}" format="MM/dd/yyyy" /></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </g:if>
            <g:else>
                <p>
                    You have no recently accomplished goals. <g:link controller="goal" action="create">Create some new goals</g:link> or
                    <g:link controller="activity" action="create">add some new activities</g:link>.
                </p>
            </g:else>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2>Recently Added Activities</h2>

            <p><g:link controller="activity">View all activities</g:link></p>

            <g:if test="${activities}">
                <table class="table table-bordered table-striped table-responsive">
                    <thead>
                        <tr>
                            <th>${message(code: 'activity.activityType.label', default: 'Activity Type')}</th>
                            <th>${message(code: 'activity.metric.label', default: 'Metric')}</th>
                            <th>${message(code: 'activity.amount.label', default: 'Amount')}</th>
                            <th>${message(code: 'activity.start.label', default: 'Start')}</th>
                            <th>${message(code: 'activity.duration.label', default: 'Duration')}</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${activities}" status="i" var="activity">
                        <tr>
                            <td><g:link controller="activity" action="show" id="${activity.id}">${fieldValue(bean: activity, field: "activityType")}</g:link></td>
                            <td>${fieldValue(bean: activity, field: "metric")}</td>
                            <td>${fieldValue(bean: activity, field: "amount")}</td>
                            <td><g:formatDate date="${activity.start}" /></td>
                            <td>${fieldValue(bean: activity, field: "duration")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </g:if>
            <g:else>
                <p>
                    You have no recent activities! Go out and do something! And <g:link controller="activity" action="create">add them here</g:link>.
                </p>
            </g:else>
        </div>
    </div>
</div>
</body>
</html>
