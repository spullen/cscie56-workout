import com.workout.User
import com.workout.Role
import com.workout.UserRole

class BootStrap {

    def init = { servletContext ->
        Role role = Role.findByAuthority('ROLE_USER')

        if(!role) {
            role = new Role(authority: 'ROLE_USER')
            role.save(flush: true)
        }

        User user = User.findByUsername('me')

        if(!user) {
            user = new User(
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
    }
    def destroy = {
    }
}
