package by.vsu.admission.office.service.api

import by.vsu.admission.office.dto.ExamDto
import by.vsu.admission.office.model.Subject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ExamService {

    fun createWithSubject(subject: Subject, examDto: ExamDto): ExamDto

    fun updateById(id: Long, examDto: ExamDto): ExamDto

    fun deleteById(id: Long)

    fun getAllDto(pageable: Pageable): Page<ExamDto>

    fun getByIdDto(id: Long): ExamDto
}