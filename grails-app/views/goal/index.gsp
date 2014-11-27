
<%@ page import="com.workout.Goal" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'goal.label', default: 'Goal')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-goal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-goal" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="goal.user.label" default="User" /></th>
					
						<g:sortableColumn property="title" title="${message(code: 'goal.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="activityType" title="${message(code: 'goal.activityType.label', default: 'Activity Type')}" />
					
						<g:sortableColumn property="metric" title="${message(code: 'goal.metric.label', default: 'Metric')}" />
					
						<g:sortableColumn property="targetAmount" title="${message(code: 'goal.targetAmount.label', default: 'Target Amount')}" />
					
						<g:sortableColumn property="targetDate" title="${message(code: 'goal.targetDate.label', default: 'Target Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${goalInstanceList}" status="i" var="goalInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${goalInstance.id}">${fieldValue(bean: goalInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: goalInstance, field: "title")}</td>
					
						<td>${fieldValue(bean: goalInstance, field: "activityType")}</td>
					
						<td>${fieldValue(bean: goalInstance, field: "metric")}</td>
					
						<td>${fieldValue(bean: goalInstance, field: "targetAmount")}</td>
					
						<td><g:formatDate date="${goalInstance.targetDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${goalInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
