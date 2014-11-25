package com.workout

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegistrationCode

@Secured(['permitAll'])
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
    def index() {
        def copy = [:] + (flash.chainedParams ?: [:])
        copy.remove 'controller'
        copy.remove 'action'
        [command: new RegisterCommand(copy)]
    }

    def register(RegisterCommand command) {

        if (command.hasErrors()) {
            render view: 'index', model: [command: command]
            return
        }

        String salt = saltSource instanceof NullSaltSource ? null : command.username
        def user = lookupUserClass().newInstance(
                email: command.email,
                username: command.username,
                firstName: command.firstName,
                lastName: command.lastName,
                phoneNumber: command.phoneNumber,
                accountLocked: false,
                enabled: true
        )

        RegistrationCode registrationCode = springSecurityUiService.register(user, command.password, salt)
        if (registrationCode == null || registrationCode.hasErrors()) {
            // null means problem creating the user
            flash.error = message(code: 'spring.security.ui.register.miscError')
            flash.chainedParams = params
            redirect action: 'index'
            return
        }

        flash.message = 'Account created! Please log in.'

        redirect controller: 'login', action: 'index'
    }
}

class RegisterCommand {
    String username
    String email
    String firstName
    String lastName
    String preferredDistanceUnits = 'mi'
    String password
    String password2

    def grailsApplication

    static constraints = {
        username blank: false, validator: { value, command ->
            if (value) {
                def User = command.grailsApplication.getDomainClass(
                        SpringSecurityUtils.securityConfig.userLookup.userDomainClassName).clazz
                if (User.findByUsername(value)) {
                    return 'registerCommand.username.unique'
                }
            }
        }
        email blank: false, email: true, validator: { value, command ->
            if (value) {
                def User = command.grailsApplication.getDomainClass(
                        SpringSecurityUtils.securityConfig.userLookup.userDomainClassName).clazz
                if (User.findByEmail(value)) {
                    return 'registerCommand.email.unique'
                }
            }
        }
        password blank: false, validator: RegisterController.passwordValidator
        password2 validator: RegisterController.password2Validator
        firstName blank: false
        lastName blank: false
        preferredDistanceUnits blank: false, inList: ['mi', 'km']
    }
}
