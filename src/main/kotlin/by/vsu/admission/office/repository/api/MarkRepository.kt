package by.vsu.admission.office.repository.api

import by.vsu.admission.office.model.Mark
import org.springframework.data.jpa.repository.JpaRepository

interface MarkRepository: JpaRepository<Mark, Long> {

    fun findAllByStudentId(studentId: Long): List<Mark>

    fun countByExamIdAndStudentId(examId: Long, studentId: Long): Long
}