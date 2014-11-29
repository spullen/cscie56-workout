<%@ page import="com.workout.Activity" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'activity.label', default: 'Workout')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<p><g:link action="index">Back to activity list</g:link></p>
				<g:if test="${activityInstance.canUpdate()}">
					<g:form url="[resource:activityInstance, action:'delete']" method="DELETE">
						<fieldset class="buttons">
							<g:link class="btn btn-info" action="edit" resource="${activityInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
							<g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</fieldset>
					</g:form>
				</g:if>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<dl>
					<dt><g:message code="activity.activityType.label" default="Activity Type" /></dt>
					<dd><g:fieldValue bean="${activityInstance}" field="activityType"/></dd>

					<dt><g:message code="activity.metric.label" default="Metric" /></dt>
					<dd><g:fieldValue bean="${activityInstance}" field="metric"/></dd>

					<dt><g:message code="activity.amount.label" default="Amount" /></dt>
					<dd><g:fieldValue bean="${activityInstance}" field="amount"/></dd>

					<dt><g:message code="activity.start.label" default="Start" /></dt>
					<dd><g:formatDate date="${activityInstance?.start}" /></dd>

					<dt><g:message code="activity.duration.label" default="Duration" /></dt>
					<dd><g:fieldValue bean="${activityInstance}" field="duration"/></dd>
				</dl>
			</div>
		</div>
		<g:if test="${activityInstance?.notes}">
			<div class="row">
				<div class="col-md-12">
					<p><g:fieldValue bean="${activityInstance}" field="notes"/></p>
				</div>
			</div>
		</g:if>
	</div>
</body>
</html>
