package by.vsu.admission.office.repository.api

import by.vsu.admission.office.model.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository: JpaRepository<Subject, Int> {
}