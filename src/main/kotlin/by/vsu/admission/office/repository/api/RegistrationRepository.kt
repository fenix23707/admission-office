package by.vsu.admission.office.repository.api

import by.vsu.admission.office.model.Registration
import org.springframework.data.jpa.repository.JpaRepository

interface RegistrationRepository: JpaRepository<Registration, Long> {
}