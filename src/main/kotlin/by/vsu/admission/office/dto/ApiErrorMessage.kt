package by.vsu.admission.office.dto

import java.time.LocalDateTime

data class ApiErrorMessage(
    val status: String,
    val message: String,
    val debugMessage: String? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
