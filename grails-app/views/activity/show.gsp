
<%@ page import="com.workout.Activity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'activity.label', default: 'Activity')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-activity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-activity" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list activity">
			
				<g:if test="${activityInstance?.activityType}">
				<li class="fieldcontain">
					<span id="activityType-label" class="property-label"><g:message code="activity.activityType.label" default="Activity Type" /></span>
					
						<span class="property-value" aria-labelledby="activityType-label"><g:fieldValue bean="${activityInstance}" field="activityType"/></span>
					
				</li>
				</g:if>

				<g:if test="${activityInstance?.metric}">
					<li class="fieldcontain">
						<span id="metric-label" class="property-label"><g:message code="activity.metric.label" default="Metric" /></span>

						<span class="property-value" aria-labelledby="metric-label"><g:fieldValue bean="${activityInstance}" field="metric"/></span>

					</li>
				</g:if>
			
				<g:if test="${activityInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="activity.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${activityInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.start}">
				<li class="fieldcontain">
					<span id="start-label" class="property-label"><g:message code="activity.start.label" default="Start" /></span>
					
						<span class="property-value" aria-labelledby="start-label"><g:formatDate date="${activityInstance?.start}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.duration}">
				<li class="fieldcontain">
					<span id="duration-label" class="property-label"><g:message code="activity.duration.label" default="Duration" /></span>
					
						<span class="property-value" aria-labelledby="duration-label"><g:fieldValue bean="${activityInstance}" field="duration"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="activity.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${activityInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:activityInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${activityInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
