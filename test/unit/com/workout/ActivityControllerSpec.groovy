package com.workout

import grails.test.mixin.*
import spock.lang.*

@TestFor(ActivityController)
@Mock(Activity)
class ActivityControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params['activityType'] = ActivityType.RUNNING
        params['amount'] = 5.2
        params['metric'] = MetricType.DISTANCE
        params['start'] = new Date('10/11/2014 10:00:00')
        params['end'] = new Date('10/11/2014 10:35:00')
        params['duration'] = 35
        params['notes'] = 'Great run'
    }

    /*
    mock user
    mock security service
    set the current user to the mocked user
    controller.springSecurityService = mockedService

    similar for mocking a nested dependency
    mock user
    mock someService
    mock springSecurityService
    mockSpringSecurityService.currentUser(or w/e it is) = mockUser
    someService.springSecurityService = mockSpringSecurityService
     */

    void "Test the index action returns the correct model"() {
        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.activityInstanceList
            model.activityInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.activityInstance!= null
    }

    void "Test the save action correctly persists an instance"() {
        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def activity = new Activity()
            activity.validate()
            controller.save(activity)

        then:"The create view is rendered again with the correct model"
            model.activityInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            activity = new Activity(params)

            controller.save(activity)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/activity/show/1'
            controller.flash.message != null
            Activity.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def activity = new Activity(params)
            controller.show(activity)

        then:"A model is populated containing the domain instance"
            model.activityInstance == activity
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def activity = new Activity(params)
            controller.edit(activity)

        then:"A model is populated containing the domain instance"
            model.activityInstance == activity
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/activity/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def activity = new Activity()
            activity.validate()
            controller.update(activity)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.activityInstance == activity

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            activity = new Activity(params).save(flush: true)
            controller.update(activity)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/activity/show/$activity.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/activity/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def activity = new Activity(params).save(flush: true)

        then:"It exists"
            Activity.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(activity)

        then:"The instance is deleted"
            Activity.count() == 0
            response.redirectedUrl == '/activity/index'
            flash.message != null
    }
}
