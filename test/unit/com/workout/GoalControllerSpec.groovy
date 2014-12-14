package com.workout

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.*
import spock.lang.*

@TestFor(GoalController)
@Mock([Goal, User, GoalService, SpringSecurityService])
class GoalControllerSpec extends Specification {

    def user

    def populateValidParams(params) {
        assert params != null
        params['user'] = user
        params['title'] = 'Some Goal Title'
        params['activityType'] = ActivityType.RUNNING
        params['metric'] = MetricType.DISTANCE
        params['targetAmount'] = 100
        params['targetDate'] = ((new Date()).clearTime() + 50)
    }

    void setup() {
        user = new User(
                username: 'testUser',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'test@user-test.com',
                preferredDistanceUnits: 'mi'
        )
        user.save(flush: true)

        def mockedSpringSecurityService = mockFor(SpringSecurityService, true)
        mockedSpringSecurityService.demand.loadCurrentUser {
            return user
        }

        def mockedGoalService = mockFor(GoalService, true)
        mockedGoalService.demand.create(1..2) { Goal g -> g.save(flush: true) }
        mockedGoalService.demand.update(1..2) { Goal g -> g.save(flush: true) }
        mockedGoalService.demand.delete(1..2) { Goal g -> g.delete(flush: true) }
        mockedGoalService.demand.isAuthorized(1..2) { Goal g -> true }

        controller.springSecurityService = mockedSpringSecurityService.createMock()
        controller.goalService = mockedGoalService.createMock()
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.goalInstanceList
            model.goalInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.goalInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def goal = new Goal()
            goal.validate()
            controller.save(goal)

        then:"The create view is rendered again with the correct model"
            model.goalInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            goal = new Goal(params)

            controller.save(goal)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/goal/show/1'
            controller.flash.message != null
            Goal.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def goal = new Goal(params)
            goal.goalActivities = []
            controller.show(goal)

        then:"A model is populated containing the domain instance"
            model.goalInstance == goal
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def goal = new Goal(params)
            controller.edit(goal)

        then:"A model is populated containing the domain instance"
            model.goalInstance == goal
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/goal/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def goal = new Goal()
            goal.validate()
            controller.update(goal)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.goalInstance == goal

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            goal = new Goal(params).save(flush: true)
            controller.update(goal)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/goal/show/$goal.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/goal/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def goal = new Goal(params).save(flush: true)

        then:"It exists"
            Goal.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(goal)

        then:"The instance is deleted"
            Goal.count() == 0
            response.redirectedUrl == '/goal/index'
            flash.message != null
    }
}
