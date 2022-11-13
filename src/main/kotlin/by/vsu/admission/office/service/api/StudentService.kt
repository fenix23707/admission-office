package by.vsu.admission.office.service.api

import by.vsu.admission.office.model.Student

interface StudentService {

    fun getById(id: Long): Student

    fun getByUserId(userId: Long): Student
}