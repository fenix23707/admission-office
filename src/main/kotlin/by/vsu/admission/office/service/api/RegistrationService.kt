package by.vsu.admission.office.service.api

import by.vsu.admission.office.model.Registration
import by.vsu.admission.office.model.Student
import by.vsu.admission.office.model.Subject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RegistrationService {

    fun getAll(pageable: Pageable): Page<Registration>

    fun getAllByStudentId(studentId: Long, pageable: Pageable): Page<Registration>

    fun register(student: Student, subject: Subject): Registration

    fun deleteById(id: Long)
}
