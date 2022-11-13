package by.vsu.admission.office.security

import by.vsu.admission.office.service.api.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class UserDetailServiceImpl @Autowired constructor(
    private val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return UserSecurity.fromUser(userService.getByUsername(username))
    }
}