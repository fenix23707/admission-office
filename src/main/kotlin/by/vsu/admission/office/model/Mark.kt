package by.vsu.admission.office.model

data class Mark(
    val id: Long? = null,
    val score: Int,
    val schedule: Schedule,
    val student: Student,
)
