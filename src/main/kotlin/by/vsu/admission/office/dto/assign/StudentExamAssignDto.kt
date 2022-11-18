package by.vsu.admission.office.dto.assign

import by.vsu.admission.office.dto.ExamDto
import by.vsu.admission.office.dto.StudentDto

data class StudentExamAssignDto(
    val student: StudentDto,
    val exam: ExamDto,
)
