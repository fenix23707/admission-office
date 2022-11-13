package by.vsu.admission.office.service.api

import by.vsu.admission.office.model.User


interface UserService {

    fun getByUsername(username: String): User
}