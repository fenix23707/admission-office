package by.vsu.admission.office.dto

import by.vsu.admission.office.model.Exam
import by.vsu.admission.office.model.Subject
import java.time.Duration
import java.time.LocalDateTime

data class ExamDto(
    val id: Long?,
    val time: LocalDateTime?,
    val duration: Duration?,
    val classroom: String?,
    val subject: Subject?,
) {
    constructor(exam: Exam) : this(
        id = exam.id,
        time = exam.time,
        duration = exam.duration,
        classroom = exam.classroom,
        subject = exam.subject
    )

    fun toModel(): Exam {
        return Exam(
            time = this.time!!,
            duration = this.duration!!,
            classroom = this.classroom!!,
            subject = this.subject!!,
            users = mutableSetOf()
        )
    }

    fun toModel(default: Exam): Exam {
        return Exam(
            id = default.id!!,
            time = this.time ?: default.time,
            duration = this.duration ?: default.duration,
            classroom = this.classroom ?: default.classroom,
            subject = default.subject,
            users = default.users
        )
    }
}
