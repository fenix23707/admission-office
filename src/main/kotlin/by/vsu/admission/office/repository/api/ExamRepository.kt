package by.vsu.admission.office.repository.api

import by.vsu.admission.office.model.Exam
import org.springframework.data.jpa.repository.JpaRepository

interface ExamRepository : JpaRepository<Exam, Long> {

    fun countById(id: Long): Long
}