package by.vsu.admission.office.repository

import by.vsu.admission.office.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String) : User?
}