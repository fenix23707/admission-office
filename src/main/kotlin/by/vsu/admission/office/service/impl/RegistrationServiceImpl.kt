package by.vsu.admission.office.service.impl

import by.vsu.admission.office.exception.notfound.RegistrationNotFoundException
import by.vsu.admission.office.model.Registration
import by.vsu.admission.office.model.RegistrationStatus
import by.vsu.admission.office.model.Student
import by.vsu.admission.office.model.Subject
import by.vsu.admission.office.repository.api.RegistrationRepository
import by.vsu.admission.office.service.api.RegistrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RegistrationServiceImpl @Autowired constructor(
    private val registrationRepository: RegistrationRepository
) : RegistrationService {

    override fun getAll(pageable: Pageable): Page<Registration> {
        return registrationRepository.findAll(pageable)
    }

    override fun getAllByStudentId(studentId: Long, pageable: Pageable): Page<Registration> {
        return registrationRepository.findAllByStudentId(studentId, pageable)
    }

    override fun register(student: Student, subject: Subject): Registration {
        val registration = Registration(
            id = null,
            status = RegistrationStatus.CREATED,
            subject = subject,
            student = student
        )
        return registrationRepository.save(registration)
    }

    override fun deleteById(id: Long) {
        getById(id)
        registrationRepository.deleteById(id)
    }

    private fun getById(id: Long): Registration {
        return registrationRepository.findByIdOrNull(id) ?: throw RegistrationNotFoundException(id)
    }

}
