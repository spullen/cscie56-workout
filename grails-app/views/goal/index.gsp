
<%@ page import="com.workout.Goal" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'goal.label', default: 'Goal')}" />
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

				<table class="table table-bordered table-striped table-responsive">
					<thead>
						<tr>
							<g:sortableColumn property="title" title="${message(code: 'goal.title.label', default: 'Title')}" />
							<g:sortableColumn property="activityType" title="${message(code: 'goal.activityType.label', default: 'Activity Type')}" />
							<g:sortableColumn property="metric" title="${message(code: 'goal.metric.label', default: 'Metric')}" />
							<g:sortableColumn property="targetAmount" title="${message(code: 'goal.targetAmount.label', default: 'Target Amount')}" />
							<g:sortableColumn property="targetDate" title="${message(code: 'goal.targetDate.label', default: 'Target Date')}" />
							<g:sortableColumn property="currentAmount" title="${message(code: 'goal.currentAmount.label', default: 'Current Amount')}" />
						</tr>
					</thead>
					<tbody>
					<g:each in="${goalInstanceList}" status="i" var="goalInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><g:link action="show" id="${goalInstance.id}">${fieldValue(bean: goalInstance, field: "title")}</g:link></td>
							<td>${fieldValue(bean: goalInstance, field: "activityType")}</td>
							<td>${fieldValue(bean: goalInstance, field: "metric")}</td>
							<td>${fieldValue(bean: goalInstance, field: "targetAmount")}</td>
							<td><g:formatDate date="${goalInstance.targetDate}" format="MM/dd/yyyy" /></td>
							<td>${fieldValue(bean: goalInstance, field: "currentAmount")}</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${goalInstanceCount ?: 0}" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
