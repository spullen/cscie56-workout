package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_USER'])
class ActivityController {

    SpringSecurityService springSecurityService
    ActivityService activityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = 'dateCreated'
        params.order = 'desc'
        def activities = Activity.where {
            user == springSecurityService.loadCurrentUser()
        }
        respond activities.list(params), model:[activityInstanceCount: activities.count()]
    }

    def show(Activity activityInstance) {
        if(!activityService.isAuthorized(activityInstance)) {
            notAuthorized()
            return
        }

        respond activityInstance
    }

    def create() {
        respond new Activity(params)
    }

    def save(Activity activityInstance) {
        if (activityInstance == null) {
            notFound()
            return
        }

        activityService.create(activityInstance)

        if (activityInstance.hasErrors()) {
            respond activityInstance.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'activity.label', default: 'Activity'), activityInstance.id])
                redirect activityInstance
            }
            '*' { respond activityInstance, [status: CREATED] }
        }
    }

    def edit(Activity activityInstance) {
        if(!activityService.isAuthorized(activityInstance)) {
            notAuthorized()
            return
        }

        if(!activityInstance.canUpdate()) {
            cannotUpdate()
            return
        }

        respond activityInstance
    }

    def update(Activity activityInstance) {
        if (activityInstance == null) {
            notFound()
            return
        }

        if(!activityService.isAuthorized(activityInstance)) {
            notAuthorized()
            return
        }

        if(!activityInstance.canUpdate()) {
            cannotUpdate()
            return
        }

        activityService.update(activityInstance)

        if (activityInstance.hasErrors()) {
            respond activityInstance.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Activity.label', default: 'Activity'), activityInstance.id])
                redirect activityInstance
            }
            '*'{ respond activityInstance, [status: OK] }
        }
    }

    def delete(Activity activityInstance) {
        if (activityInstance == null) {
            notFound()
            return
        }

        if(!activityService.isAuthorized(activityInstance)) {
            notAuthorized()
            return
        }

        if(!activityInstance.canUpdate()) {
            cannotUpdate()
            return
        }

        activityService.delete(activityInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Activity.label', default: 'Activity'), activityInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    private void cannotUpdate() {
        flash.warning = message(code: 'default.cannotUpdate.message')
        redirect action:"index", method:"GET"
    }

    private void notAuthorized() {
        flash.warning = message(code: 'default.unauthorized.message')
        redirect action:"index", method:"GET"
    }
}
