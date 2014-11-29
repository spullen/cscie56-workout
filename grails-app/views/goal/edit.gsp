<%@ page import="com.workout.Goal" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'goal.label', default: 'Goal')}" />
	<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
		</div>
	</div>
	<div class="container">
		<g:hasErrors bean="${goalInstance}">
			<div class="row">
				<div class="col-md-12">
					<ul class="alert alert-danger" role="alert">
						<g:eachError bean="${goalInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
						</g:eachError>
					</ul>
				</div>
			</div>
		</g:hasErrors>

		<div class="row">
			<div class="col-md-12">
				<g:form url="[resource:goalInstance, action:'update']" class="form-horizontal" method="PUT" >
					<g:hiddenField name="version" value="${goalInstance?.version}" />
					<g:render template="form"/>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</div>
</body>
</html>
