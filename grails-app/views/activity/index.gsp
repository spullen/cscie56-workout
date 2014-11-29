<%@ page import="com.workout.Activity" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'activity.label', default: 'Workout')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<p><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></p>

				<g:if test="${activityInstanceList}">
					<table class="table table-responsive table-bordered table-striped">
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
						<g:each in="${activityInstanceList}" status="i" var="activityInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td><g:link action="show" id="${activityInstance.id}">${fieldValue(bean: activityInstance, field: "activityType")}</g:link></td>
								<td>${fieldValue(bean: activityInstance, field: "metric")}</td>
								<td>${fieldValue(bean: activityInstance, field: "amount")}</td>
								<td><g:formatDate date="${activityInstance.start}" /></td>
								<td>${fieldValue(bean: activityInstance, field: "duration")}</td>
							</tr>
						</g:each>
						</tbody>
					</table>
					<div class="pagination">
						<g:paginate total="${activityInstanceCount ?: 0}" />
					</div>
				</g:if>
				<g:else>
					<p>You have no workouts logged yet!</p>
				</g:else>
			</div>
		</div>
	</div>
</body>
</html>
