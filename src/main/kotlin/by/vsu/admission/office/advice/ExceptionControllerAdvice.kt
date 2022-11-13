package by.vsu.admission.office.advice

import by.vsu.admission.office.dto.ApiErrorMessage
import by.vsu.admission.office.exception.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(ServiceException::class)
    fun serviceExceptionHandler(ex: ServiceException): ResponseEntity<ApiErrorMessage> {
        val apiErrorMessage = ApiErrorMessage(
            status = ex.status.name,
            message = ex.message,
        )
        return ResponseEntity(apiErrorMessage, ex.status)
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(exception: Exception): ResponseEntity<ApiErrorMessage> {
        val apiErrorMessage = ApiErrorMessage(
            status = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = "Something went wrong",
            debugMessage = exception.message
        )
        return ResponseEntity(apiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}