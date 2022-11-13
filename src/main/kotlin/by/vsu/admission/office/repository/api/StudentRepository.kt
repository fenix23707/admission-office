package by.vsu.admission.office.repository.api

import by.vsu.admission.office.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {

    fun findByUserId(userId: Long): Student?
}