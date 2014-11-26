
<%@ page import="com.workout.Activity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'activity.label', default: 'Activity')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-activity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-activity" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="activity.user.label" default="User" /></th>
					
						<g:sortableColumn property="activityType" title="${message(code: 'activity.activityType.label', default: 'Activity Type')}" />
					
						<g:sortableColumn property="amount" title="${message(code: 'activity.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="metric" title="${message(code: 'activity.metric.label', default: 'Metric')}" />
					
						<g:sortableColumn property="start" title="${message(code: 'activity.start.label', default: 'Start')}" />
					
						<g:sortableColumn property="end" title="${message(code: 'activity.end.label', default: 'End')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${activityInstanceList}" status="i" var="activityInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${activityInstance.id}">${fieldValue(bean: activityInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: activityInstance, field: "activityType")}</td>
					
						<td>${fieldValue(bean: activityInstance, field: "amount")}</td>
					
						<td>${fieldValue(bean: activityInstance, field: "metric")}</td>
					
						<td><g:formatDate date="${activityInstance.start}" /></td>
					
						<td><g:formatDate date="${activityInstance.end}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${activityInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
