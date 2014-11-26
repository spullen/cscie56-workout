import com.workout.User
import com.workout.Role
import com.workout.UserRole

class BootStrap {

    def init = { servletContext ->
        Role role = new Role(authority: 'ROLE_USER').save(flush: true)

        User user = new User(
                username: 'me',
                password: 'password',
                firstName: 'Test',
                lastName: 'User',
                email: 'testUser@test.com',
                preferredDistanceUnits: 'mi'
        )
        user.save(flush: true)

        UserRole.create(user, role)
    }
    def destroy = {
    }
}
