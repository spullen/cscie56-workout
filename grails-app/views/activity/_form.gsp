<%@ page import="com.workout.Activity" %>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'activityType', 'error')} required">
	<label for="activityType">
		<g:message code="activity.activityType.label" default="Activity Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="activityType" from="${com.workout.ActivityType?.values()}" keys="${com.workout.ActivityType.values()*.name()}" required="" value="${activityInstance?.activityType?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="activity.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" value="${fieldValue(bean: activityInstance, field: 'amount')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'metric', 'error')} required">
	<label for="metric">
		<g:message code="activity.metric.label" default="Metric" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="metric" from="${com.workout.MetricType?.values()}" keys="${com.workout.MetricType.values()*.name()}" required="" value="${activityInstance?.metric?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'start', 'error')} ">
	<label for="start">
		<g:message code="activity.start.label" default="Start" />
		
	</label>
	<g:datePicker name="start" precision="day"  value="${activityInstance?.start}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'end', 'error')} ">
	<label for="end">
		<g:message code="activity.end.label" default="End" />
		
	</label>
	<g:datePicker name="end" precision="day"  value="${activityInstance?.end}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'duration', 'error')} ">
	<label for="duration">
		<g:message code="activity.duration.label" default="Duration" />
		
	</label>
	<g:field name="duration" value="${fieldValue(bean: activityInstance, field: 'duration')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="activity.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="2000" value="${activityInstance?.notes}"/>

</div>

