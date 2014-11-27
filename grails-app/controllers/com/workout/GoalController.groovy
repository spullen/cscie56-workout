package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class GoalController {

    SpringSecurityService springSecurityService
    GoalService goalService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        User user = springSecurityService.loadCurrentUser()
        def goals = Goal.findAllByUser(user, params)
        respond goals, model:[goalInstanceCount: Goal.countByUser(user)]
    }

    def show(Goal goalInstance) {
        respond goalInstance
    }

    def create() {
        respond new Goal(params)
    }

    def save(Goal goalInstance) {
        if (goalInstance == null) {
            notFound()
            return
        }

        goalService.create(goalInstance)

        if (goalInstance.hasErrors()) {
            respond goalInstance.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'goal.label', default: 'Goal'), goalInstance.id])
                redirect goalInstance
            }
            '*' { respond goalInstance, [status: CREATED] }
        }
    }

    def edit(Goal goalInstance) {
        if(!isAuthorized(goalInstance)) {
            notAuthorized()
            return
        }

        respond goalInstance
    }

    def update(Goal goalInstance) {
        if (goalInstance == null) {
            notFound()
            return
        }

        if(!isAuthorized(goalInstance)) {
            notAuthorized()
            return
        }

        goalService.update(goalInstance)

        if (goalInstance.hasErrors()) {
            respond goalInstance.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Goal.label', default: 'Goal'), goalInstance.id])
                redirect goalInstance
            }
            '*'{ respond goalInstance, [status: OK] }
        }
    }

    def delete(Goal goalInstance) {
        if (goalInstance == null) {
            notFound()
            return
        }

        if(!isAuthorized(goalInstance)) {
            notAuthorized()
            return
        }

        goalService.delete(goalInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Goal.label', default: 'Goal'), goalInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'goal.label', default: 'Goal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    private boolean isAuthorized(Goal goal) {
        User user = springSecurityService.loadCurrentUser()
        return goal.user.id == user.id
    }

    private void notAuthorized() {
        flash.warning = message(code: 'default.unauthorized.message')
        redirect action:"index", method:"GET"
    }
}
