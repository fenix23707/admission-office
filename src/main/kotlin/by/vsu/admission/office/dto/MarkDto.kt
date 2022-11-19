package by.vsu.admission.office.dto

import by.vsu.admission.office.model.Exam
import by.vsu.admission.office.model.Mark
import by.vsu.admission.office.model.Student

data class MarkDto(
    val id: Long?,
    val score: Int?,
    val exam: Exam?,
    val student: Student?,
) {

    constructor(mark: Mark): this(
        id = mark.id,
        score = mark.score,
        exam = mark.exam,
        student = mark.student
    )

    fun toModel(exam: Exam, student: Student): Mark {
        return Mark(
            score = this.score!!,
            exam = exam,
            student = student
        )
    }
}
