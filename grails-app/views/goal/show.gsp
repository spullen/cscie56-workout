<%@ page import="com.workout.Goal" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'goal.label', default: 'Goal')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-goal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-goal" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list goal">
			
				<g:if test="${goalInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="goal.title.label" default="Title" /></span>
					<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${goalInstance}" field="title"/></span>
				</li>
				</g:if>
			
				<g:if test="${goalInstance?.activityType}">
				<li class="fieldcontain">
					<span id="activityType-label" class="property-label"><g:message code="goal.activityType.label" default="Activity Type" /></span>
					<span class="property-value" aria-labelledby="activityType-label"><g:fieldValue bean="${goalInstance}" field="activityType"/></span>
				</li>
				</g:if>
			
				<g:if test="${goalInstance?.metric}">
				<li class="fieldcontain">
					<span id="metric-label" class="property-label"><g:message code="goal.metric.label" default="Metric" /></span>
					<span class="property-value" aria-labelledby="metric-label"><g:fieldValue bean="${goalInstance}" field="metric"/></span>
				</li>
				</g:if>
			
				<g:if test="${goalInstance?.targetAmount}">
				<li class="fieldcontain">
					<span id="targetAmount-label" class="property-label"><g:message code="goal.targetAmount.label" default="Target Amount" /></span>
					<span class="property-value" aria-labelledby="targetAmount-label"><g:fieldValue bean="${goalInstance}" field="targetAmount"/></span>
				</li>
				</g:if>
			
				<g:if test="${goalInstance?.targetDate}">
				<li class="fieldcontain">
					<span id="targetDate-label" class="property-label"><g:message code="goal.targetDate.label" default="Target Date" /></span>
					<span class="property-value" aria-labelledby="targetDate-label"><g:formatDate date="${goalInstance?.targetDate}" format="MM/dd/yyyy" /></span>
				</li>
				</g:if>
			
				<g:if test="${goalInstance?.currentAmount}">
				<li class="fieldcontain">
					<span id="currentAmount-label" class="property-label"><g:message code="goal.currentAmount.label" default="Current Amount" /></span>
					<span class="property-value" aria-labelledby="currentAmount-label"><g:fieldValue bean="${goalInstance}" field="currentAmount"/></span>
				</li>
				</g:if>
			
				<g:if test="${goalInstance?.dateAccomplished}">
				<li class="fieldcontain">
					<span id="dateAccomplished-label" class="property-label"><g:message code="goal.dateAccomplished.label" default="Date Accomplished" /></span>
					<span class="property-value" aria-labelledby="dateAccomplished-label"><g:formatDate date="${goalInstance?.dateAccomplished}" format="MM/dd/yyyy" /></span>
				</li>
				</g:if>
			
				<g:if test="${goalInstance?.accomplished}">
				<li class="fieldcontain">
					<span id="accomplished-label" class="property-label"><g:message code="goal.accomplished.label" default="Accomplished" /></span>
					<span class="property-value" aria-labelledby="accomplished-label"><g:formatBoolean boolean="${goalInstance?.accomplished}" /></span>
				</li>
				</g:if>
			</ol>
			<g:form url="[resource:goalInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${goalInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
