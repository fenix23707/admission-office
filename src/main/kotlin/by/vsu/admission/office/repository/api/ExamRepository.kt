package by.vsu.admission.office.repository.api

import by.vsu.admission.office.model.Exam
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ExamRepository : JpaRepository<Exam, Long> {

    fun countById(id: Long): Long
}