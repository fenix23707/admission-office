package by.vsu.admission.office.repository

import by.vsu.admission.office.model.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository: JpaRepository<Subject, Int> {
}