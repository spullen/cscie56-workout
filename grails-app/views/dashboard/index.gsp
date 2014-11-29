
<%@ page import="com.workout.Activity" %>
<%@ page import="com.workout.Goal" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Workout Dashboard</title>
</head>
<body>
<div role="main">
    <div class="page-header">
        Dashboard
    </div>
    <div>
        <h2>Active Goals</h2>

        <g:if test="${activeGoals}">
            <table>
                <thead>
                    <tr>
                        <tr>${message(code: 'goal.title.label', default: 'Title')}</tr>
                        <tr>${message(code: 'goal.activityType.label', default: 'Activity Type')}</tr>
                        <tr>${message(code: 'goal.metric.label', default: 'Metric')}</tr>
                        <tr>${message(code: 'goal.targetAmount.label', default: 'Target Amount')}</tr>
                        <tr>${message(code: 'goal.targetDate.label', default: 'Target Date')}</tr>
                        <tr>${message(code: 'goal.currentAmount.label', default: 'Current Amount')}</tr>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${activeGoals}" status="i" var="activeGoal">
                        <tr>
                            <td><g:link controller="goal" action="show" id="${activeGoal.id}">${fieldValue(bean: activeGoal, field: "title")}</g:link></td>
                            <td>${fieldValue(bean: activeGoal, field: "activityType")}</td>
                            <td>${fieldValue(bean: activeGoal, field: "metric")}</td>
                            <td>${fieldValue(bean: activeGoal, field: "targetAmount")}</td>
                            <td><g:formatDate date="${activeGoal.targetDate}" /></td>
                            <td>${fieldValue(bean: activeGoal, field: "currentAmount")}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>

        </g:else>
    </div>
    <div>
        <h2>Recently Accomplished Goals</h2>

        <g:if test="${accomplishedGoals}">

        </g:if>
        <g:else>

        </g:else>
    </div>
    <div>
        <h2>Recently Added Activities</h2>

        <g:if test="${activities}">

        </g:if>
        <g:else>

        </g:else>
    </div>
</div>
</body>
</html>
