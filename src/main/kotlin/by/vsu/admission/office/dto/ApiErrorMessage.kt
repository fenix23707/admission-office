package by.vsu.admission.office.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import java.time.LocalDateTime

@JsonInclude(Include.NON_NULL)
data class ApiErrorMessage(
    val status: String,
    val message: String,
    val debugMessage: String? = null,
    val stackTrace: String? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
