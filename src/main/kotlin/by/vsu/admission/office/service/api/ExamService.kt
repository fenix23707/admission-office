package by.vsu.admission.office.service.api

import by.vsu.admission.office.dto.assign.StudentExamAssignDto
import by.vsu.admission.office.dto.ExamDto
import by.vsu.admission.office.model.Exam
import by.vsu.admission.office.model.Subject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ExamService {

    fun getById(id: Long): Exam

    fun createWithSubject(subject: Subject, examDto: ExamDto): ExamDto

    fun updateById(id: Long, examDto: ExamDto): ExamDto

    fun deleteById(id: Long)

    fun assignStudent(examId: Long, studentId: Long): StudentExamAssignDto

    fun getAllDto(pageable: Pageable): Page<ExamDto>

    fun getByIdDto(id: Long): ExamDto

    fun getAllByUserIdDto(studentId: Long): List<ExamDto>

    fun checkHasAccessToExam(userId: Long, examId: Long)
}