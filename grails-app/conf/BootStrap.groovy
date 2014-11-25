import com.workout.User

class BootStrap {

    def init = { servletContext ->
        User user = new User(
                username: 'me',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'testUser@test.com',
                preferredDistanceUnits: 'mi'
        )
        user.save(flush: true)
    }
    def destroy = {
    }
}
