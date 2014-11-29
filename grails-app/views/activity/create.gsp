<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'activity.label', default: 'Workout')}" />
	<title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
		</div>
	</div>
	<div class="container">
		<g:hasErrors bean="${activityInstance}">
			<div class="row">
				<div class="col-md-12">
					<ul class="alert alert-danger" role="alert">
						<g:eachError bean="${activityInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
						</g:eachError>
					</ul>
				</div>
			</div>
		</g:hasErrors>

		<div class="row">
			<div class="col-md-12">
				<g:form url="[resource:activityInstance, action:'save']" class="form-horizontal" novalidate="novalidate" method="POST">
					<g:hiddenField name="version" value="${activityInstance?.version}" />
					<g:render template="form"/>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<g:submitButton name="create" class="btn btn-primary" value="${message(code: 'default.button.create.label', default: 'Create')}" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</div>
</body>
</html>
