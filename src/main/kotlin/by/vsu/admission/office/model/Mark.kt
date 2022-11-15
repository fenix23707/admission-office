package by.vsu.admission.office.model

data class Mark(
    val id: Long? = null,
    val score: Int,
    val exam: Exam,
    val student: Student,
)
