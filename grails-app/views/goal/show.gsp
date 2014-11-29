<%@ page import="com.workout.Goal" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'goal.label', default: 'Goal')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1><g:fieldValue bean="${goalInstance}" field="title"/></h1>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<p><g:link action="index">Back to goal list</g:link></p>

				<g:form url="[resource:goalInstance, action:'delete']" method="DELETE">
					<fieldset class="buttons">
						<g:link class="btn btn-info" action="edit" resource="${goalInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<dl>
					<dt><g:message code="goal.activityType.label" default="Activity Type" /></dt>
					<dd><g:fieldValue bean="${goalInstance}" field="activityType"/></dd>

					<dt><g:message code="goal.metric.label" default="Metric" /></dt>
					<dd><g:fieldValue bean="${goalInstance}" field="metric"/></dd>

					<dt><g:message code="goal.targetAmount.label" default="Target Amount" /></dt>
					<dd><g:fieldValue bean="${goalInstance}" field="targetAmount"/></dd>

					<dt><g:message code="goal.targetDate.label" default="Target Date" /></dt>
					<dd><g:formatDate date="${goalInstance?.targetDate}" format="MM/dd/yyyy" /></dd>

					<dt><g:message code="goal.currentAmount.label" default="Current Amount" /></dt>
					<dd><g:fieldValue bean="${goalInstance}" field="currentAmount"/></dd>

					<g:if test="${goalInstance?.accomplished}">
						<dt><g:message code="goal.dateAccomplished.label" default="Date Accomplished" /></dt>
						<dd><g:formatDate date="${goalInstance?.dateAccomplished}" format="MM/dd/yyyy" /></dd>
					</g:if>
				</dl>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<g:if test="${activities}">
					<h2>Workouts</h2>

					<p><g:link controller="activity" action="create">Add Workout</g:link></p>

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
					<p>You have no workouts logged for this goal! Want to <g:link controller="activity" action="create">add one?</g:link></p>
				</g:else>
			</div>
		</div>
	</div>
</body>
</html>
