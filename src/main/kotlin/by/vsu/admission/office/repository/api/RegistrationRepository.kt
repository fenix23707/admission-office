package by.vsu.admission.office.repository.api

import by.vsu.admission.office.model.Registration
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface RegistrationRepository : JpaRepository<Registration, Long> {

    fun findAllByStudentId(studentId: Long, pageable: Pageable): Page<Registration>
}