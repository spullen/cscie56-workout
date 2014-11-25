package com.workout

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(User)
class UserConstraintsSpec extends Specification {

    User user
    User existingUser

    def setup() {
        user = new User(
                username: 'newUser',
                password: 'password',
                firstName: 'New',
                lastName: 'User',
                email: 'newUser@test.com'
        )

        existingUser = new User(
                username: 'existing',
                password: 'password',
                firstName: 'New',
                lastName: 'User',
                email: 'existing@test.com'
        )

        mockForConstraintsTests(User, [existingUser])
    }

    void "firstName"() {
        when: 'firstName is null'
        user.firstName = null

        then: 'validation should fail'
        !user.validate()
        user.errors['firstName'] == 'nullable'

        when: 'firstName is blank'
        user.firstName = ''

        then: 'validation should fail'
        !user.validate()
        user.errors['firstName'] == 'blank'

        when: 'firstName is not null or blank'
        user.firstName = 'Test'

        then: 'validation should pass'
        user.validate()
        !user.hasErrors()
    }

    void "lastName"() {
        when: 'lastName is null'
        user.lastName = null

        then: 'validation should fail'
        !user.validate()
        user.errors['lastName'] == 'nullable'

        when: 'lastName is blank'
        user.lastName = ''

        then: 'validation should fail'
        !user.validate()
        user.errors['lastName'] == 'blank'

        when: 'lastName is not null or blank'
        user.lastName = 'Test'

        then: 'validation should pass'
        user.validate()
        !user.hasErrors()
    }

    void "email"() {
        when: 'email is null'
        user.email = null

        then: 'validation should fail'
        !user.validate()
        user.errors['email'] == 'nullable'

        when: 'email is blank'
        user.email = ''

        then: 'validation should fail'
        !user.validate()
        user.errors['email'] == 'blank'

        when: 'email is an invalid email address'
        user.email = 'garbage'

        then: 'validation should fail'
        !user.validate()
        user.errors['email'] == 'email'

        when: 'email has already been taken'
        user.email = existingUser.email

        then: 'validation should fail'
        !user.validate()
        user.errors['email'] == 'unique'

        when: 'email is a valid email address'
        user.email = 'test@test.com'

        then: 'validation should pass'
        user.validate()
        !user.hasErrors()
    }
}