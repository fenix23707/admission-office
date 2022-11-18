package by.vsu.admission.office.dto

import by.vsu.admission.office.model.Student

data class StudentDto(
    val id: Long?
) {
    constructor(student: Student): this(student.id)
}
