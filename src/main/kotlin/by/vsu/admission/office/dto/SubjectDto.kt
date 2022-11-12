package by.vsu.admission.office.dto

import by.vsu.admission.office.model.Subject

data class SubjectDto(
    val id: Int?,
    val name: String?,
) {
    constructor(subject: Subject): this(subject.id, subject.name)
}
