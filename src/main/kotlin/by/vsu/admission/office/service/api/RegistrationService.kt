package by.vsu.admission.office.service.api

import by.vsu.admission.office.model.Registration
import by.vsu.admission.office.model.Student
import by.vsu.admission.office.model.Subject

interface RegistrationService {

    fun register(student: Student, subject: Subject): Registration
}