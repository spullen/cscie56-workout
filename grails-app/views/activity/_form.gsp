<%@ page import="com.workout.Activity" %>
<%@ page import="com.workout.ActivityType" %>
<%@ page import="com.workout.MetricType" %>

<div class="form-group ${hasErrors(bean: activityInstance, field: 'activityType', 'has-error')} required">
	<label class="col-md-2 control-label" for="activityType">
		<g:message code="activity.activityType.label" default="Activity Type" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:select name="activityType"
				  from="${ActivityType.values()}"
				  keys="${ActivityType.values()*.name()}"
				  required=""
				  class="form-control"
				  value="${activityInstance?.activityType?.name()}" />
	</div>
</div>

<div class="form-group ${hasErrors(bean: activityInstance, field: 'metric', 'has-error')} required">
	<label class="col-md-2 control-label" for="metric">
		<g:message code="activity.metric.label" default="Metric" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:select name="metric" from="${MetricType?.values()}" keys="${MetricType.values()*.name()}" required="" class="form-control" value="${activityInstance?.metric?.name()}" />
	</div>
</div>

<div class="form-group ${hasErrors(bean: activityInstance, field: 'amount', 'has-error')} required">
	<label class="col-md-2 control-label" for="amount">
		<g:message code="activity.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:field name="amount" value="${fieldValue(bean: activityInstance, field: 'amount')}" class="form-control" required=""/>
	</div>
</div>

<div class="form-group ${hasErrors(bean: activityInstance, field: 'start', 'has-error')} ">
	<label class="col-md-2 control-label" for="start">
		<g:message code="activity.start.label" default="Start" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:datePicker name="start" precision="minute" value="${activityInstance?.start}" default="${new Date()}" class="form-control" relativeYears="[-1..0]" noSelection="['': '']" />
	</div>
</div>

<div class="form-group ${hasErrors(bean: activityInstance, field: 'duration', 'has-error')} ">
	<label class="col-md-2 control-label" for="duration">
		<g:message code="activity.duration.label" default="Duration (in minutes)" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:field name="duration" value="${fieldValue(bean: activityInstance, field: 'duration')}" class="form-control"/>
	</div>
</div>

<div class="form-group ${hasErrors(bean: activityInstance, field: 'notes', 'has-error')} ">
	<label class="col-md-2 control-label" for="notes">
		<g:message code="activity.notes.label" default="Notes" />
	</label>
	<div class="col-md-6">
		<g:textArea name="notes" cols="40" rows="5" maxlength="2000" value="${activityInstance?.notes}" class="form-control" />
	</div>
</div>

