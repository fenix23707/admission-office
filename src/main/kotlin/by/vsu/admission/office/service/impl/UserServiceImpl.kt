package by.vsu.admission.office.service.impl

import by.vsu.admission.office.exception.notfound.UserNotFoundException
import by.vsu.admission.office.model.User
import by.vsu.admission.office.repository.api.UserRepository
import by.vsu.admission.office.service.api.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : UserService {

    override fun getByUsername(username: String): User {
        return userRepository.findByUsername(username) ?: throw UserNotFoundException(username)
    }
}