package by.vsu.admission.office.service.api

import by.vsu.admission.office.dto.MarkDto
import by.vsu.admission.office.model.Mark

interface MarkService {

    fun getById(id: Long): Mark

    fun create(markDto: MarkDto, examId: Long, studentId: Long): MarkDto

    fun delete(id: Long)

    fun getAllDtoByStudentId(studentId: Long): List<MarkDto>
}