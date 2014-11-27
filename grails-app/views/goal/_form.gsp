<%@ page import="com.workout.Goal" %>
<%@ page import="com.workout.ActivityType" %>
<%@ page import="com.workout.MetricType" %>

<div class="fieldcontain ${hasErrors(bean: goalInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="goal.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" maxlength="150" required="" value="${goalInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goalInstance, field: 'activityType', 'error')} required">
	<label for="activityType">
		<g:message code="goal.activityType.label" default="Activity Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="activityType" from="${ActivityType?.values()}" keys="${ActivityType.values()*.name()}" required="" value="${goalInstance?.activityType?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: goalInstance, field: 'metric', 'error')} required">
	<label for="metric">
		<g:message code="goal.metric.label" default="Metric" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="metric" from="${MetricType?.values()}" keys="${MetricType.values()*.name()}" required="" value="${goalInstance?.metric?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: goalInstance, field: 'targetAmount', 'error')} required">
	<label for="targetAmount">
		<g:message code="goal.targetAmount.label" default="Target Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="targetAmount" value="${fieldValue(bean: goalInstance, field: 'targetAmount')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: goalInstance, field: 'targetDate', 'error')} required">
	<label for="targetDate">
		<g:message code="goal.targetDate.label" default="Target Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="targetDate" precision="day"  value="${goalInstance?.targetDate}"  />

</div>

