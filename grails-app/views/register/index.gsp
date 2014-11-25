<html>

<head>
	<meta name='layout' content='register'/>
	<title><g:message code='spring.security.ui.register.title'/></title>
</head>

<body>

<p/>

<s2ui:form width='650' height='300' elementId='loginFormContainer'
           titleCode='spring.security.ui.register.description' center='true'>

<g:form action='register' name='registerForm'>

	<g:if test='${emailSent}'>
	<br/>
	<g:message code='spring.security.ui.register.sent'/>
	</g:if>
	<g:else>

	<br/>

	<table>
	<tbody>
		<s2ui:textFieldRow name='firstName' bean="${command}" value="${command.firstName}"
					   size='40' labelCode='user.firstName.label' labelCodeDefault='First Name'/>

		<s2ui:textFieldRow name='lastName' bean="${command}" value="${command.lastName}"
						   size='40' labelCode='user.lastName.label' labelCodeDefault='Last Name'/>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="preferredDistanceUnits"><g:message code="user.preferredDistanceUnits.label" default="Preferred Distance Units" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: command, field: 'preferredDistanceUnits', 'errors')}">
				<g:select name="preferredDistanceUnits" from="${command.constraints.preferredDistanceUnits.inList}" value="${command?.preferredDistanceUnits}" />
				<span class="s2ui_error">${fieldError(bean: command, field: 'preferredDistanceUnits')}</span>
			</td>
		</tr>

		<s2ui:textFieldRow name='username' labelCode='user.username.label' bean="${command}"
                         size='40' labelCodeDefault='Username' value="${command.username}"/>

		<s2ui:textFieldRow name='email' bean="${command}" value="${command.email}"
		                   size='40' labelCode='user.email.label' labelCodeDefault='Email'/>

		<s2ui:passwordFieldRow name='password' labelCode='user.password.label' bean="${command}"
                             size='40' labelCodeDefault='Password' value="${command.password}"/>

		<s2ui:passwordFieldRow name='password2' labelCode='user.password2.label' bean="${command}"
                             size='40' labelCodeDefault='Password (again)' value="${command.password2}"/>

	</tbody>
	</table>

	<s2ui:submitButton elementId='create' form='registerForm' messageCode='spring.security.ui.register.submit'/>

	</g:else>

</g:form>

</s2ui:form>

<script>
$(document).ready(function() {
	$('#username').focus();
});
</script>

</body>
</html>
