package by.vsu.admission.office.controller

import by.vsu.admission.office.model.Registration
import by.vsu.admission.office.security.UserSecurity
import by.vsu.admission.office.service.api.RegistrationService
import by.vsu.admission.office.service.api.StudentService
import by.vsu.admission.office.service.api.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/registrations")
class RegistrationController @Autowired constructor(
    private val registrationService: RegistrationService,
    private val studentService: StudentService,
    private val subjectService: SubjectService,
) {

    @PostMapping("/subjects/{subjectId}")
    @PreAuthorize("hasAuthority('REGISTRATION_CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@PathVariable subjectId: Int, authentication: Authentication): Registration {
        val student = studentService.getByUserId((authentication.principal as UserSecurity).id)
        val subject = subjectService.getById(subjectId)
        return registrationService.register(student, subject)
    }
}