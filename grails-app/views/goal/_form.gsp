<%@ page import="com.workout.Goal" %>
<%@ page import="com.workout.ActivityType" %>
<%@ page import="com.workout.MetricType" %>

<div class="form-group ${hasErrors(bean: goalInstance, field: 'title', 'has-error')}">
	<label class="col-md-2 control-label" for="title">
		<g:message code="goal.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:textField class="form-control" name="title" maxlength="150" required="" value="${goalInstance?.title}"/>
	</div>
</div>

<div class="form-group ${hasErrors(bean: goalInstance, field: 'activityType', 'has-error')}">
	<label class="col-md-2 control-label" for="activityType">
		<g:message code="goal.activityType.label" default="Activity Type" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:select name="activityType"
				  from="${ActivityType?.values()}"
				  keys="${ActivityType.values()*.name()}"
				  class="form-control"
				  required=""
				  value="${goalInstance?.activityType?.name()}" />
	</div>
</div>

<div class="form-group ${hasErrors(bean: goalInstance, field: 'metric', 'has-error')} required">
	<label class="col-md-2 control-label" for="metric">
		<g:message code="goal.metric.label" default="Metric" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:select class="form-control"
				  name="metric" from="${MetricType?.values()}"
				  keys="${MetricType.values()*.name()}"
				  required=""
				  value="${goalInstance?.metric?.name()}" />
	</div>
</div>

<div class="form-group ${hasErrors(bean: goalInstance, field: 'targetAmount', 'has-error')}">
	<label class="col-md-2 control-label" for="targetAmount">
		<g:message code="goal.targetAmount.label" default="Target Amount" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:field class="form-control" name="targetAmount" value="${fieldValue(bean: goalInstance, field: 'targetAmount')}" required=""/>
	</div>
</div>

<div class="form-group ${hasErrors(bean: goalInstance, field: 'targetDate', 'has-error')}">
	<label class="col-md-2 control-label" for="targetDate">
		<g:message code="goal.targetDate.label" default="Target Date" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-md-4">
		<g:datePicker class="form-control" name="targetDate" precision="day" value="${goalInstance?.targetDate}" relativeYears="[0..1]"  />
	</div>
</div>

